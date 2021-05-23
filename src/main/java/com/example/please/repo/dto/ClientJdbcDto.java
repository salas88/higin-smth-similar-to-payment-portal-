//package com.example.please.repo.dto;
//
//import com.example.please.entity.Account;
//import com.example.please.entity.Client;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ClientJdbcDto implements ClientDto<Client>{
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//
//    public final static RowMapper<Client> clientMapper = new BeanPropertyRowMapper<>(Client.class);
//    public final static RowMapper<Account> accountMapper = new BeanPropertyRowMapper<>(Account.class);
//
//
//
//    @Override
//    public List<Client> findAll() {
//        return jdbcTemplate.query("select * from client", rs -> {
//            List<Client> clients = new ArrayList<>();
//            Client currentClient = null;
//            int id = 0;
//            int accId = 0;
//
//            while (rs.next()){
//                currentClient = clientMapper.mapRow(rs, id++);
//                clients.add(currentClient);
//
//                currentClient.addItem(accountMapper.mapRow(rs,accId++));
//            }
//            return clients;
//        });
//    }
//
//    public List<Account> findAcc(){
//        return jdbcTemplate.query("Select * from account", rs -> {
//            List<Account> accounts = new ArrayList<>();
//            Account curentAcc = null;
//            int id = 0;
//            while (rs.next()){
//                id = rs.getInt("id");
//                curentAcc = accountMapper.mapRow(rs, id++);
//                curentAcc.setClient(clientMapper.mapRow(rs, 0));
//
//                accounts.add(curentAcc);
//
//            }
//            return accounts;
//        });
//    }
//
//    public Account findAccById(int id){
//
//        return jdbcTemplate.query("select * from account where id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Account.class))
//                .stream().findAny().orElse(null);
//    }
//
//
//}
