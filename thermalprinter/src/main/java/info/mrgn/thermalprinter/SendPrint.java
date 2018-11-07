package info.mrgn.thermalprinter;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.UnknownHostException;

public class SendPrint {

    public SendPrint(final String ip, final String content, final Response respondStatus){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {

                    java.net.Socket sock = new java.net.Socket(ip, 9100);
                    PrintWriter oStream = new PrintWriter(sock.getOutputStream());
                    oStream.println(content);
                    oStream.close();
                    sock.close();
                    respondStatus.success(200,"success");



                    //---------------------------------------------------------

//                    java.net.Socket socket = new java.net.Socket(ip, 9100);
//                    OutputStream outputStream = socket.getOutputStream();
//
//                    InputStream inputStream = getAssets().open("mail_logo.jpg");
//
//                    byte[] buffer = new byte[8192];
//                    int bytesRead;
//                    ByteArrayOutputStream output = new ByteArrayOutputStream();
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        output.write(buffer, 0, bytesRead);
//                    }
//
//                    byte[] size = ByteBuffer.allocate(4).putInt(output.size()).array();
//
//                    outputStream.write(size);
//                    outputStream.write(output.toByteArray());
//                    outputStream.flush();
//                    System.out.println("Flushed: " + System.currentTimeMillis());
//
//                    Thread.sleep(120000);
//                    System.out.println("Closing: " + System.currentTimeMillis());
//                    socket.close();


                }
                catch (UnknownHostException e)
                {
                    e.printStackTrace();
                    Log.d("THERMALPRINTER","unknown host");
                    Log.d("THERMALPRINTER",e.getMessage());
                    respondStatus.failed(404,"Printer not found");
                } catch (ConnectException e){
                    if(e.toString().contains("ECONNREFUSED")){
                        Log.d("THERMALPRINTER","retrying since error");
                    }
                    respondStatus.failed(301,"Connection Error");

                } catch (IOException e)
                {
                    e.printStackTrace();
                    Log.d("THERMALPRINTER",e.getMessage());
                    respondStatus.failed(404, "Printer not found");
                }
            }


        });
        thread.start();
    }
}
