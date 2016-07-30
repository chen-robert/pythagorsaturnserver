/**
 * Fast.java (Maze Server)
 *
 * Copyright 2016 Finn Bear. All rights reserved.
 */

package mazeserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.io.StringWriter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

 
import java.io.IOException;

public class FastServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);
        ServletContextHandler ctx = new ServletContextHandler();
        ctx.setContextPath("/");
        ctx.addServlet(FastSocketServlet.class, "/saturnbackend");
        server.setHandler(ctx);
        server.start();
        server.join();
    }

    @WebSocket
    private static class FastWebSocket {

        private Game _game;
        private Session _session;
        private long _sessionId;


        // This method is called when the connection is closed.
        @OnWebSocketClose
        public void handleClose(int statusCode, String reason) {
            trace("Connection closed with statusCode=" + statusCode + ", reason=" + reason);
        }

        // This method is called when the socket connection with the browser is established.
        @OnWebSocketConnect
        public void handleConnect(Session session) {
            _session = session;
        }

        // This method is called in case of an error.
        @OnWebSocketError
        public void handleError(Throwable e) {
            // trace(e.getStackTrace());
        }

        // This method is called when a message is received from the browser.
        @OnWebSocketMessage
        public void handleMessage(String message) {
            assert _game != null;
            assert _sessionId != 0L;

            JsonReader reader = new JsonReader(new StringReader(message));
            Action action = new Gson().fromJson(reader, Action.class);
            _game.applyAction(_sessionId, action);

            // TBD: When appropriate, call stop() method.

            StringWriter out = new StringWriter();
            new GsonBuilder().create().toJson(_game, out);
            send(out.toString());
        }
     
        // This method sends a message to the browser.
        private void send(String message) {
            try {
                if (_session.isOpen()) {
                    _session.getRemote().sendString(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     
        // This method closes the socket.
        private void stop() {
            try {
                _session.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void trace(String message) {
            // TBD
        }
    }

    private static class FastSocketServlet extends WebSocketServlet {

        @Override
        public void configure(WebSocketServletFactory factory) {
            factory.register(FastWebSocket.class);
        }
    }
}
