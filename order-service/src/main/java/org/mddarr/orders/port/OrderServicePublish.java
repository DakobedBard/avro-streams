package org.mddarr.orders.port;
import org.mddarr.orders.beans.OrderDTO;


/**
 * Created by <a href="mailto:pasquale.paola@gmail.com">Pasquale Paola</a> on 09/09/19.
 */
public interface OrderServicePublish {
    void sendOrder(OrderDTO orderDTO);
}
