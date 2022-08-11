package com.example.client.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quickfix.Message;
import quickfix.MessageCracker;
import quickfix.*;
import quickfix.fix44.*;

@Service
@Slf4j
public class FixClientApplication extends MessageCracker implements Application {

    /**
     * On create.
     *
     * @param sessionId the session id
     */
    @Override
    public void onCreate(SessionID sessionId) {
        log.info("启动时候调用此方法创建:{}", sessionId);
    }

    /**
     * On logon.
     *
     * @param sessionId the session id
     */
    @Override
    public void onLogon(SessionID sessionId) {
        log.info("客户端登陆成功时候调用此方法:{}", sessionId);
    }

    /**
     * On logout.
     *
     * @param sessionId the session id
     */
    @Override
    public void onLogout(SessionID sessionId) {
        log.info("客户端断开连接时候调用此方法:{}", sessionId);
    }

    /**
     * To admin.
     *
     * @param message the message
     * @param sessionId the session id
     */
    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        log.info("发送会话消息时候调用此方法,sessionId={}, message={}", sessionId, message);
    }

    /**
     * From admin.
     *
     * @param message the message
     * @param sessionId the session id
     * @throws FieldNotFound the field not found
     * @throws IncorrectDataFormat the incorrect data format
     * @throws IncorrectTagValue the incorrect tag value
     * @throws RejectLogon the reject logon
     */
    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectTagValue {
        log.info("接收会话类型消息时调用此方法,sessionId={}, message={}", sessionId, message);
        try {
            crack(message, sessionId);
        } catch (UnsupportedMessageType unsupportedMessageType) {
            unsupportedMessageType.printStackTrace();
        }
    }

    /**
     * To app.
     *
     * @param message the message
     * @param sessionId the session id
     * @throws DoNotSend the do not send
     */
    @Override
    public void toApp(Message message, SessionID sessionId) {
        log.info("发送业务消息时候调用此方法,sessionId={}, message={}", sessionId, message);
    }

    /**
     * From app.
     *
     * @param message the message
     * @param sessionId the session id
     * @throws FieldNotFound the field not found
     * @throws IncorrectDataFormat the incorrect data format
     * @throws IncorrectTagValue the incorrect tag value
     * @throws UnsupportedMessageType the unsupported message type
     */
    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectTagValue, UnsupportedMessageType {
        log.info("接收业务消息时调用此方法:sessionId={},message={}", sessionId, message);
        crack(message, sessionId);
    }

    /**
     * On message.
     *
     * @param message the message
     * @param sessionID the session id
     */
    @Override
    public void onMessage(Message message, SessionID sessionID) {
        log.info("message,sessionId={}, message:{}", sessionID, message);
    }

    /**
     * On heart beat.
     *
     * @param message the message
     * @param sessionID the session id
     */
    @Handler
    public void onHeartBeat(Heartbeat message, SessionID sessionID) {
        log.info("heartbeat message,sessionId={}, message:{}", sessionID, message);
    }

    /**
     * On log out message.
     *
     * @param message the message
     * @param sessionID the session id
     */
    @Handler
    public void onLogOutMessage(Logout message, SessionID sessionID) {
        log.info("logout message,sessionId={}, message:{}", sessionID, message);
    }

    /**
     * On logon message.
     *
     * @param message the message
     * @param sessionID the session id
     */
    @Handler
    public void onLogonMessage(Logon message, SessionID sessionID) {
        log.info("logon message,sessionId={}, message:{}", sessionID, message);
    }

    /**
     * On order cancel message.
     *
     * @param message the message
     * @param sessionId the session id
     * @throws FieldNotFound the field not found
     */
    @Handler
    public void onOrderCancelMessage(OrderCancelRequest message, SessionID sessionId) {
        log.info("receive order cancel msg, cancel={}", message);
    }

    /**
     * On execution report message.
     *
     * @param message the message
     * @param sessionID the session id
     * @throws FieldNotFound the field not found
     */
    @Handler
    public void onExecutionReportMessage(ExecutionReport message, SessionID sessionID) {
        log.info("recived message:{}", message.toString());
    }

    /**
     * On reject message.
     *
     * @param message the message
     * @param sessionID the session id
     * @throws FieldNotFound the field not found
     */
    @Handler
    public void onRejectMessage(Reject message, SessionID sessionID) {
        log.info("receive order reject msg, reject={}", message);
    }


    @Handler
    public void onQuoteMessage(Quote quote, SessionID sessionID) {
        log.info("receive order quote msg, quote={}", quote);
    }


    @Handler
    public void onMarketDataQuoteMessage(MarketDataSnapshotFullRefresh marketDataSnapshotFullRefresh, SessionID sessionID) {
        log.info("receive order marketDataSnapshotFullRefresh msg, marketDataSnapshotFullRefresh={}", marketDataSnapshotFullRefresh);
    }

    @Handler
    public void onMarketDataQuoteMessage(MarketDataRequestReject marketDataRequestReject, SessionID sessionID) {
        log.info("receive MarketDataRequestReject meg, marketDataRequestReject={}", marketDataRequestReject);
    }


    @Handler
    public void onQuoteRejectMessage(QuoteRequestReject quoteRequestReject, SessionID sessionID) {
        log.info("receive quoteRequestReject msg, quoteRequestReject={}", quoteRequestReject);
    }

}