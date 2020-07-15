package com.cudocom.barang.util;

public class ConnectivityReceiver /*extends BroadcastReceiver */ {

//    private static SmsListener mListener;
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Bundle data = intent.getExtras();
//
//        Object[] pdus = new Object[0];
//        if (data != null) {
//            pdus = (Object[]) data.get("pdus");
//        }
//
//        if (pdus != null) {
//            for (Object pdus1 : pdus) {
//                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus1);
//
//                String sender = smsMessage.getDisplayOriginatingAddress();
//
//                if(sender != null) {
//                    if (sender.equals(Constant.SENDER_OTP)) {
//                        String messageBody = smsMessage.getMessageBody();
//                        mListener.messageReceived(messageBody);
//                    }
//                }
//            }
//        }
//    }
//
//    public static void bindListener(SmsListener listener) {
//        mListener = listener;
//    }
}