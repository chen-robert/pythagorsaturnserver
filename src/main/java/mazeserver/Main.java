/**
 * Maze Server
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */

package mazeserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class Main extends HttpServlet {
    // This indicates the dimensions of the maze.
    private static final int _MAZE_HEIGHT = 20;
    private static final int _MAZE_WIDTH = 20;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        assert req != null;
        assert resp != null;
        PrintWriter out = resp.getWriter();

        try {
            String p = req.getServletPath();
            assert p != null;
            boolean debugging = p.equals("/");

            if (debugging) {
                maze(new Date().getTime(), out, true);
            } else {
                String[] a = p.split("/");
                String arg = a.length < 2 ? null : a[1];
                maze(parseMazeId(arg), out, false);
            }

            resp.setStatus(HttpStatus.OK_200);
        } catch (Throwable ex) {
            out.println(ex.toString());
            resp.setStatus(HttpStatus.OK_200);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        assert req != null;
        assert resp != null;
        PrintWriter out = resp.getWriter();
        try {
            // id = req.getParameter("id");
            String p = req.getServletPath();
            assert p != null;
            String[] a = p.split("/");
            String arg = a.length < 2 ? null : a[1];
            assert arg != null;
            long mazeId = parseMazeId(arg);

            String line;
            BufferedReader input = req.getReader();
            while ((line = input.readLine()) != null) {
                out.print(parseJson(line));
            }

            resp.setStatus(HttpStatus.OK_200);
        } catch (Throwable ex) {
            out.println(ex.toString());
            resp.setStatus(HttpStatus.OK_200);
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(server, "/saturnbackend");
        handler.addServlet(Main.class, "/");
        server.start();
    }

    private void maze(long mazeId, PrintWriter out, boolean debugging) {

        Maze maze = new Maze(mazeId, _MAZE_HEIGHT, _MAZE_WIDTH);
        Gson gson = new GsonBuilder().create();

        if (debugging) {
            out.println("<html>\n<title>maze</title>\n<body>");
        }

        // SVG
        if (debugging) {
            StringBuffer svg = new StringBuffer();
            final int scale = 15;
            final int unscaledPadding = Math.max(_MAZE_HEIGHT / 2, _MAZE_WIDTH / 2);
            final int scaledPadding = scale * unscaledPadding;
            final int scaledHeight = scale * _MAZE_HEIGHT;
            final int scaledWidth = scale * _MAZE_WIDTH;

            final int xBounds = scaledPadding / 2;
            final int yBounds = xBounds;
            final int xCenter = (scaledPadding + scaledWidth) / 2;
            final int yCenter = (scaledPadding + scaledHeight) / 2;
            svg.append(MessageFormat.format( "<svg height=\"{0}\" width=\"{1}\">\n",
                scaledPadding + scaledHeight, scaledPadding + scaledWidth ));

            // Bounding box and center (x, y).
            svg.append(MessageFormat.format("  <rect x=\"{0}\" y=\"{1}\" height=\"{2}\" width=\"{3}\" style=\"stroke-width:1;stroke:rgb(0,0,255);fill:rgb(255,255,255);stroke-opacity:0.2;\" />\n", xBounds, yBounds, scaledHeight, scaledWidth));
            svg.append(MessageFormat.format( "  <line x1=\"{0}\" y1=\"{1}\" x2=\"{2}\" y2=\"{3}\" style=\"stroke:rgb(0,0,255);stroke-opacity:0.2;stroke-width:1\" />\n", xCenter, yBounds, xCenter, yBounds + scaledHeight));
            svg.append(MessageFormat.format( "  <line x1=\"{0}\" y1=\"{1}\" x2=\"{2}\" y2=\"{3}\" style=\"stroke:rgb(0,0,255);stroke-opacity:0.2;stroke-width:1\" />\n", xBounds, yCenter, xBounds + scaledWidth, yCenter));

            for (Maze.Line line : maze.getContent().getLines()) {
                svg.append(MessageFormat.format( "  <line x1=\"{0}\" y1=\"{1}\" x2=\"{2}\" y2=\"{3}\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />\n",
                    xCenter + scale * line.getStart().getX(),
                    yCenter + scale * line.getStart().getY(),
                    xCenter + scale * line.getEnd().getX(),
                    yCenter + scale * line.getEnd().getY() ));
            }
            svg.append("</svg>\n");
            out.println(svg.toString());
        }

        // JSON
        if (debugging) {
            out.println("<h1>json</h1>\n<a href=\"" + mazeId + "\">JSON only</a>\n<pre>\n");
        }
        gson.toJson(maze.getContent(), out);
        if (debugging) {
            out.println("\n</pre>");
            out.println("<form action=\"" + mazeId + "\" enctype=\"multipart/form-data\" method=\"POST\" name=\"play\">");
            out.println("<input name=\"json\" type=\"file\" />");
            out.println("<input type=\"submit\" value=\"Play\" />");
            out.println("</form>");
            out.println("</body>\n</html>");
        }
    }

    private static String parseJson(String arg) {
        String json = null;
        if (arg != null) {
            int i = arg.indexOf("{");
            int j = arg.lastIndexOf("}");
            if (i >= 0 && j >= 0 && i < j) {
                json = arg.substring(i, j + 1);
            } else {
                json = "";
            }
        }
        return json;
    }

    private static long parseMazeId(String arg) {
        long mazeId;
        try {
            mazeId = Long.parseLong(arg);
        } catch (NumberFormatException ex) {
            mazeId = 13579111315L;
        }
        return mazeId;
    }
}
