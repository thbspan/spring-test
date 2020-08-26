package com.test.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.entity.Address;
import com.test.entity.Order;
import com.test.entity.OrderItem;
import com.test.mapper.AddressMapper;
import com.test.mapper.OrderItemMapper;
import com.test.mapper.OrderMapper;
import com.test.service.ExampleService;

@Primary
@Service
public class OrderServiceImpl implements ExampleService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private AddressMapper addressMapper;
    @Override
    public void initEnvironment() throws SQLException {
        orderMapper.createTableIfNotExists();
        orderItemMapper.createTableIfNotExists();
        orderMapper.truncateTable();
        orderItemMapper.truncateTable();
        initAddressTable();
    }

    private void initAddressTable() throws SQLException {
        addressMapper.createTableIfNotExists();
        addressMapper.truncateTable();
        for (int i = 1; i <= 10; i++) {
            Address entity = new Address();
            entity.setAddressId((long) i);
            entity.setAddressName("address_" + i);
            addressMapper.insert(entity);
        }
    }

    @Override
    public void cleanEnvironment() throws SQLException {
//        orderMapper.dropTable();
//        orderItemMapper.dropTable();
    }

    @Override
//    @Transactional
    public void processSuccess() throws SQLException {
        System.out.println("-------------- Process Success Begin ---------------");
        List<Long> orderIds = insertData();
        printData();
        deleteData(orderIds);
        printData();
        System.out.println("-------------- Process Success Finish --------------");
    }

    @Override
    @Transactional
    public void processFailure() throws SQLException {
        System.out.println("-------------- Process Failure Begin ---------------");
        insertData();
        System.out.println("-------------- Process Failure Finish --------------");
        throw new RuntimeException("Exception occur for transaction test.");
    }

    private List<Long> insertData() throws SQLException {
        System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setUserId(i);
            order.setAddressId(i);
            order.setStatus("INSERT_TEST");
            orderMapper.insert(order);
            OrderItem item = new OrderItem();
            item.setOrderId(order.getOrderId());
            item.setUserId(i);
            item.setStatus("INSERT_TEST");
            orderItemMapper.insert(item);
            result.add(order.getOrderId());
        }
        return result;
    }

    private void deleteData(final List<Long> orderIds) throws SQLException {
        System.out.println("---------------------------- Delete Data ----------------------------");
        for (Long each : orderIds) {
            orderMapper.delete(each);
            orderItemMapper.delete(each);
        }
    }
    @Override
    public void printData() throws SQLException {
        System.out.println("---------------------------- Print Order Data -----------------------");
        for (Object each : orderMapper.selectAll()) {
            System.out.println(each);
        }
        System.out.println("---------------------------- Print OrderItem Data -------------------");
        for (Object each : orderItemMapper.selectAll()) {
            System.out.println(each);
        }
    }
}
