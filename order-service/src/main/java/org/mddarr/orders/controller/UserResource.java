package org.mddarr.orders.controller;

import org.mddarr.orders.event.dto.Event1;
import org.mddarr.orders.event.dto.Order;
import org.mddarr.orders.service.AvroProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
    private static final Logger log = LoggerFactory.getLogger(UserResource.class);
    private final AvroProducer producer;

    @Autowired
    UserResource(AvroProducer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam("name") String name, @RequestParam("value") String value) {
        this.producer.sendEvent1(new Event1(name, value));
        return "hey";
//        this.producer.sendEvent1();
    }


    @PostMapping("/orders/")
    public String product(@RequestParam(value="products") List<Long> products, @RequestParam(value="quantities") List<Long> quantities,
                          @RequestParam(value="cid")  Long cid, @RequestParam(value="price") Long price)
    {

        UUID uuid =  UUID.randomUUID();
        Order order = new Order(uuid.toString(),1L,products,quantities);
        this.producer.sendOrder(order);
        return "order";
    }
    @GetMapping("/async")
    public DeferredResult<ResponseEntity<?>> handleReqDefResult(Model model) {
        log.info("Received async-deferredresult request");
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            log.info("Processing in separate thread");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
            }
            output.setResult(ResponseEntity.ok("ok"));
        });

        log.info("servlet thread freed");
        return output;
    }

    @GetMapping("/timeout")
    public DeferredResult<ResponseEntity<?>> handleReqWithTimeouts(Model model) {
        log.info("Received async request with a configured timeout");
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>(500l);
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                deferredResult.setErrorResult(
                        ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
            }
        });
        ForkJoinPool.commonPool().submit(() -> {
            log.info("Processing in separate thread");
            try {
                Thread.sleep(600l);
                deferredResult.setResult(ResponseEntity.ok("ok"));
            } catch (InterruptedException e) {
                log.info("Request processing interrupted");
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("INTERNAL_SERVER_ERROR occurred."));
            }

        });
        log.info("servlet thread freed");
        return deferredResult;
    }

    @GetMapping("/fail")
    public DeferredResult<ResponseEntity<?>> handleAsyncFailedRequest(Model model) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        ForkJoinPool.commonPool().submit(() -> {
            try {
                // Exception occurred in processing
                throw new Exception();
            } catch (Exception e) {
                log.info("Request processing failed");
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("INTERNAL_SERVER_ERROR occurred."));
            }
        });
        return deferredResult;
    }


    @GetMapping("/test")
    DeferredResult<String> test(){
        Long timeOutInMilliSec = 100000L;
        String timeOutResp = "Time Out.";
        DeferredResult<String> deferredResult = new DeferredResult<>(timeOutInMilliSec,timeOutResp);
        CompletableFuture.runAsync(()->{
            try {
                //Long pooling task;If task is not completed within 100 sec timeout response retrun for this request
                TimeUnit.SECONDS.sleep(10);
                //set result after completing task to return response to client
                deferredResult.setResult("Task Finished");
            }catch (Exception ex){
            }
        });
        return deferredResult;
    }
}