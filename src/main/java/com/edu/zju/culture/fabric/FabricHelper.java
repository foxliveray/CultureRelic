package com.edu.zju.culture.fabric;

import cn.filoop.sdk.client.SDKClient;
import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.common.solidity.Abi;
import cn.hyperchain.sdk.common.utils.ByteUtil;
import cn.hyperchain.sdk.common.utils.FileUtil;
import cn.hyperchain.sdk.common.utils.FuncParams;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.ServiceManager;
import cn.hyperchain.sdk.transaction.Transaction;
import com.edu.zju.culture.mbg.entity.ExitEntry;
import com.edu.zju.culture.mbg.entity.Movement;
import com.edu.zju.culture.mbg.entity.Order;
import com.edu.zju.culture.mbg.entity.Relic;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FabricHelper {
    /**
     * 私钥小工具中可以看到的账户地址与私钥,请注意：每天都可能有变化！！！
     *
     */
    public static String json = "{\"address\":\"0x86224aa3a00e557b66d6400bfd07db353eafa435\",\"algo\":\"0x03\",\"encrypted\":\"21a0013a137a0140eb622f41dbbeab256487e1549e3475e13329650184f9c335\",\"version\":\"2.0\",\"privateKeyEncrypted\":false}";
    public static SDKClient sdkClient;
    public static AccountService accountService;
    public static Account account;

    public static void main(String[] args) throws Exception{
        FabricHelper fabricHelper=new FabricHelper();
        fabricHelper.init();
        fabricHelper.getRelic(1);

    }
    //SDK初始化
    public void init(){
        //初始化SDKClient
        sdkClient = new SDKClient();
        //设置sdk初始化参数
        sdkClient.setAppKey("i7ySZb50jNUVtkAywV01");
        sdkClient.setAppSecret("Cbm2toOqT8CQZ32mF9tMdbtbsNFcNd");
        sdkClient.setUuid("9a4eac3c-6834-11ea-8006-000000000000");//合约UUid
        //sdk 初始化，将获取鉴权token、操作区块链的证书文件
        sdkClient.init();
        // 创建交易发起账号地址
        accountService = ServiceManager.getAccountService(sdkClient.getProviderManager());
        account = accountService.fromAccountJson(json, "");
        System.out.println("账户2： "+account.toJson());
    }
    //文物上链接口
    public void addRelic(Relic relic) throws IOException {
        //加载本地合约bin、abi，当本地存在bin、abi时，可按下面方式直接加载bin、abi
        InputStream inputStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("solidity/CultureRelic.abi");
        String abiStr = FileUtil.readFile(inputStream2);
        Abi abi = Abi.fromJson(abiStr);
        //构造交易参数
        FuncParams params = new FuncParams();
        params.addParams(relic.getRelicId());
        params.addParams(relic.getGovNum());
        params.addParams(relic.getRelicName());
        params.addParams(relic.getRelicDescribe());
        params.addParams(relic.getPicture());
        params.addParams(relic.getIdentityStatus());
        params.addParams(relic.getRelicStatus());
        params.addParams(relic.getIdentityId());
        params.addParams(relic.getOwnerId());
        //构造交易
        Transaction transaction1 = new Transaction.EVMBuilder(account.getAddress()).invoke("0x9b39d1f3a30575e56ad1e618de66b66afc0b45f3", "addRelic(uint256,string,string,string,string,uint256,uint256,uint256,uint256)", abi,params).build();
        transaction1.sign(account);
        //通过sdkClient调用合约
        ReceiptResponse receiptResponse1 = sdkClient.invoke(transaction1);
        System.out.println(receiptResponse1);

        System.out.println("==============================返回数据解码==============================\n");
        byte[] bytes = ByteUtil.fromHex(receiptResponse1.getRet());
        List<?> objects = abi.getFunction("addRelic(uint256,string,string,string,string,uint256,uint256,uint256,uint256)").decodeResult(bytes);
        objects.forEach(s-> System.out.println(s));

    }
    //根据ID查询链上的文物信息
    public Relic getRelic(int relicId) throws IOException {

        //加载本地合约bin、abi，当本地存在bin、abi时，可按下面方式直接加载bin、abi
        InputStream inputStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("solidity/CultureRelic.abi");
        String abiStr = FileUtil.readFile(inputStream2);
        Abi abi = Abi.fromJson(abiStr);
        FuncParams params = new FuncParams();
        params.addParams(relicId);
        //构造交易
        Transaction transaction1 = new Transaction.EVMBuilder(account.getAddress()).invoke("0x9b39d1f3a30575e56ad1e618de66b66afc0b45f3", "relic(uint256)", abi,params).build();
        transaction1.sign(account);
        //通过sdkClient调用合约
        ReceiptResponse receiptResponse1 = sdkClient.invoke(transaction1);
        System.out.println(receiptResponse1);
        System.out.println("==============================返回数据解码==============================\n");
        byte[] bytes = ByteUtil.fromHex(receiptResponse1.getRet());
        List<?> objects = abi.getFunction("relic(uint256)").decodeResult(bytes);
        Relic relic=new Relic();
        BigInteger brelicId= (BigInteger) objects.get(0);
        BigInteger bIdentityStatus=(BigInteger)objects.get(5);
        BigInteger bRelicStatus=(BigInteger)objects.get(6);
        BigInteger bIdentityId=(BigInteger)objects.get(7);
        BigInteger bOwnerId=(BigInteger)objects.get(8);
        relic.setRelicId(brelicId.longValue());
        relic.setGovNum((String)objects.get(1));
        relic.setRelicName((String)objects.get(2));
        relic.setRelicDescribe((String)objects.get(3));
        relic.setPicture((String)objects.get(4));
        relic.setIdentityStatus(bIdentityStatus.intValue());
        relic.setRelicStatus(bRelicStatus.intValue());
        relic.setIdentityId(bIdentityId.longValue());
        relic.setOwnerId(bOwnerId.longValue());
        System.out.println(relic.getRelicName());
        return relic;

    }

    //文物交易上链接口
    public void addOrder(Order order) throws IOException {
        //加载本地合约bin、abi，当本地存在bin、abi时，可按下面方式直接加载bin、abi
        InputStream inputStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("solidity/CultureRelic.abi");
        String abiStr = FileUtil.readFile(inputStream2);
        Abi abi = Abi.fromJson(abiStr);
        //构造交易参数
        FuncParams params = new FuncParams();
        params.addParams(order.getOrderId());
        params.addParams(order.getOrderValue());
        DateTimeFormatter fmt24 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String dateStr24 = order.getOrderDate().format(fmt24);
        params.addParams(dateStr24); //24小时转为String
        params.addParams(order.getOrderStatus());
        params.addParams(order.getRelicId());
        params.addParams(order.getBuyerId());
        params.addParams(order.getSellerId());
        params.addParams(order.getCheckStatus());
        params.addParams(order.getOrderResponse());
        //构造交易
        Transaction transaction1 = new Transaction.EVMBuilder(account.getAddress()).invoke("0x9b39d1f3a30575e56ad1e618de66b66afc0b45f3", "addOrder(uint256,uint256,string,uint256,uint256,uint256,uint256,string,string)", abi,params).build();
        transaction1.sign(account);
        //通过sdkClient调用合约
        ReceiptResponse receiptResponse1 = sdkClient.invoke(transaction1);
        System.out.println(receiptResponse1);

        System.out.println("==============================返回数据解码==============================\n");
        byte[] bytes = ByteUtil.fromHex(receiptResponse1.getRet());
        List<?> objects = abi.getFunction("addOrder(uint256,uint256,string,uint256,uint256,uint256,uint256,string,string)").decodeResult(bytes);
        objects.forEach(s-> System.out.println(s));

    }
    //根据ID查询链上的文物交易信息
    public Order getOrder(int orderId) throws IOException {

        //加载本地合约bin、abi，当本地存在bin、abi时，可按下面方式直接加载bin、abi
        InputStream inputStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("solidity/CultureRelic.abi");
        String abiStr = FileUtil.readFile(inputStream2);
        Abi abi = Abi.fromJson(abiStr);
        FuncParams params = new FuncParams();
        params.addParams(orderId);
        //构造交易
        Transaction transaction1 = new Transaction.EVMBuilder(account.getAddress()).invoke("0x9b39d1f3a30575e56ad1e618de66b66afc0b45f3", "order(uint256)", abi,params).build();
        transaction1.sign(account);
        //通过sdkClient调用合约
        ReceiptResponse receiptResponse1 = sdkClient.invoke(transaction1);
        System.out.println(receiptResponse1);
        System.out.println("==============================返回数据解码==============================\n");
        byte[] bytes = ByteUtil.fromHex(receiptResponse1.getRet());
        List<?> objects = abi.getFunction("order(uint256)").decodeResult(bytes);
        Order order=new Order();
        BigInteger borderId= (BigInteger) objects.get(0);
        BigInteger borderValue=(BigInteger)objects.get(1);
        BigInteger borderStatus=(BigInteger)objects.get(3);
        BigInteger brelicId=(BigInteger)objects.get(4);
        BigInteger bbuyerId=(BigInteger)objects.get(5);
        BigInteger bsellerId=(BigInteger)objects.get(6);
        String str=(String) objects.get(2);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        LocalDateTime datetime = LocalDateTime.parse(str, fmt);
        order.setOrderId(borderId.longValue());
        order.setOrderValue(borderValue.intValue());
        order.setOrderDate(datetime);
        order.setOrderStatus(borderStatus.intValue());
        order.setRelicId(brelicId.longValue());
        order.setBuyerId(bbuyerId.longValue());
        order.setSellerId(bsellerId.longValue());
        order.setCheckStatus((String)objects.get(7));
        order.setOrderResponse((String)objects.get(8));

        //System.out.println(order.getOrderValue());
        return order;

    }

    //文物流转上链接口
    public void addMovement(Movement movement) throws IOException {
        //加载本地合约bin、abi，当本地存在bin、abi时，可按下面方式直接加载bin、abi
        InputStream inputStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("solidity/CultureRelic.abi");
        String abiStr = FileUtil.readFile(inputStream2);
        Abi abi = Abi.fromJson(abiStr);
        //构造交易参数
        FuncParams params = new FuncParams();
        params.addParams(movement.getMovementId());
        params.addParams(movement.getExplanation());
        params.addParams(movement.getMoveType());
        DateTimeFormatter fmt24 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String dateStr24 = movement.getMoveDate().format(fmt24);
        params.addParams(dateStr24); //24小时转为String
        params.addParams(movement.getRelicId());
        params.addParams(movement.getFromId());
        params.addParams(movement.getToId());
        params.addParams(movement.getCheckStatus());
        params.addParams(movement.getMovementResponse());
        //构造交易
        Transaction transaction1 = new Transaction.EVMBuilder(account.getAddress()).invoke("0x9b39d1f3a30575e56ad1e618de66b66afc0b45f3", "addMovement(uint256,string,string,string,uint256,uint256,uint256,uint256,string)", abi,params).build();
        transaction1.sign(account);
        //通过sdkClient调用合约
        ReceiptResponse receiptResponse1 = sdkClient.invoke(transaction1);
        System.out.println(receiptResponse1);

        System.out.println("==============================返回数据解码==============================\n");
        byte[] bytes = ByteUtil.fromHex(receiptResponse1.getRet());
        List<?> objects = abi.getFunction("addMovement(uint256,string,string,string,uint256,uint256,uint256,uint256,string)").decodeResult(bytes);
        objects.forEach(s-> System.out.println(s));

    }
    //根据ID查询链上的文物流转信息
    public Movement getMovement(int movementId) throws IOException {

        //加载本地合约bin、abi，当本地存在bin、abi时，可按下面方式直接加载bin、abi
        InputStream inputStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("solidity/CultureRelic.abi");
        String abiStr = FileUtil.readFile(inputStream2);
        Abi abi = Abi.fromJson(abiStr);
        FuncParams params = new FuncParams();
        params.addParams(movementId);
        //构造交易
        Transaction transaction1 = new Transaction.EVMBuilder(account.getAddress()).invoke("0x9b39d1f3a30575e56ad1e618de66b66afc0b45f3", "movement(uint256)", abi,params).build();
        transaction1.sign(account);
        //通过sdkClient调用合约
        ReceiptResponse receiptResponse1 = sdkClient.invoke(transaction1);
        System.out.println(receiptResponse1);
        System.out.println("==============================返回数据解码==============================\n");
        byte[] bytes = ByteUtil.fromHex(receiptResponse1.getRet());
        List<?> objects = abi.getFunction("movement(uint256)").decodeResult(bytes);
        Movement movement=new Movement();
        BigInteger bmovementId= (BigInteger) objects.get(0);
        BigInteger brelicId=(BigInteger)objects.get(4);
        BigInteger bfromId=(BigInteger)objects.get(5);
        BigInteger btoId=(BigInteger)objects.get(6);
        BigInteger bcheckStatus=(BigInteger)objects.get(7);

        String str=(String) objects.get(3);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        LocalDateTime datetime = LocalDateTime.parse(str, fmt);
        movement.setMovementId(bmovementId.longValue());
        movement.setExplanation((String)objects.get(1));
        movement.setMoveType((String)objects.get(2));
        movement.setMoveDate(datetime);
        movement.setRelicId(brelicId.longValue());
        movement.setFromId(bfromId.longValue());
        movement.setToId(btoId.longValue());
        movement.setCheckStatus(bcheckStatus.intValue());
        movement.setMovementResponse((String)objects.get(8));

        //System.out.println(movement.getExplanation());
        return movement;

    }


    //文物出入境信息上链接口
    public void addExitEntry(ExitEntry exitEntry) throws IOException {
        //加载本地合约bin、abi，当本地存在bin、abi时，可按下面方式直接加载bin、abi
        InputStream inputStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("solidity/CultureRelic.abi");
        String abiStr = FileUtil.readFile(inputStream2);
        Abi abi = Abi.fromJson(abiStr);
        //构造交易参数
        FuncParams params = new FuncParams();
        params.addParams(exitEntry.getExitEntryId());
        params.addParams(exitEntry.getFromId());
        params.addParams(exitEntry.getToId());
        params.addParams(exitEntry.getRelicId());
        params.addParams(exitEntry.getOrigin());
        params.addParams(exitEntry.getDestination());
        params.addParams(exitEntry.getExitEntryCheckStatus());
        params.addParams(exitEntry.getExitEntryResponse());
        params.addParams(exitEntry.getExitEntryCustomsStatus());
        params.addParams(exitEntry.getExitEntryCustomsResponse());
        //构造交易
        Transaction transaction1 = new Transaction.EVMBuilder(account.getAddress()).invoke("0x9b39d1f3a30575e56ad1e618de66b66afc0b45f3", "addExitEntry(uint256,uint256,uint256,uint256,string,string,uint256,string,uint256,string)", abi,params).build();
        transaction1.sign(account);
        //通过sdkClient调用合约
        ReceiptResponse receiptResponse1 = sdkClient.invoke(transaction1);
        System.out.println(receiptResponse1);

        System.out.println("==============================返回数据解码==============================\n");
        byte[] bytes = ByteUtil.fromHex(receiptResponse1.getRet());
        List<?> objects = abi.getFunction("addExitEntry(uint256,uint256,uint256,uint256,string,string,uint256,string,uint256,string)").decodeResult(bytes);
        objects.forEach(s-> System.out.println(s));

    }
    //根据ID查询链上的文物出入境信息
    public ExitEntry getExitEntry(int exitEntryId) throws IOException {

        //加载本地合约bin、abi，当本地存在bin、abi时，可按下面方式直接加载bin、abi
        InputStream inputStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("solidity/CultureRelic.abi");
        String abiStr = FileUtil.readFile(inputStream2);
        Abi abi = Abi.fromJson(abiStr);
        FuncParams params = new FuncParams();
        params.addParams(exitEntryId);
        //构造交易
        Transaction transaction1 = new Transaction.EVMBuilder(account.getAddress()).invoke("0x9b39d1f3a30575e56ad1e618de66b66afc0b45f3", "exitEntry(uint256)", abi,params).build();
        transaction1.sign(account);
        //通过sdkClient调用合约
        ReceiptResponse receiptResponse1 = sdkClient.invoke(transaction1);
        System.out.println(receiptResponse1);
        System.out.println("==============================返回数据解码==============================\n");
        byte[] bytes = ByteUtil.fromHex(receiptResponse1.getRet());
        List<?> objects = abi.getFunction("exitEntry(uint256)").decodeResult(bytes);
        ExitEntry exitEntry=new ExitEntry();
        BigInteger bexitEntryId= (BigInteger) objects.get(0);
        BigInteger bfromId=(BigInteger)objects.get(1);
        BigInteger btoId=(BigInteger)objects.get(2);
        BigInteger brelicId=(BigInteger)objects.get(3);
        BigInteger bexitEntryCheckStatus=(BigInteger)objects.get(6);
        BigInteger bexitEntryCustomsStatus=(BigInteger)objects.get(8);

        exitEntry.setExitEntryId(bexitEntryId.longValue());
        exitEntry.setFromId(bfromId.longValue());
        exitEntry.setToId(btoId.longValue());
        exitEntry.setRelicId(brelicId.longValue());
        exitEntry.setOrigin((String)objects.get(4));
        exitEntry.setDestination((String)objects.get(5));
        exitEntry.setExitEntryCheckStatus(bexitEntryCheckStatus.intValue());
        exitEntry.setExitEntryResponse((String)objects.get(7));
        exitEntry.setExitEntryCustomsStatus(bexitEntryCustomsStatus.intValue());
        exitEntry.setExitEntryCustomsResponse((String)objects.get(9));
        //System.out.println(exitEntry.getDestination());
        return exitEntry;

    }


}
