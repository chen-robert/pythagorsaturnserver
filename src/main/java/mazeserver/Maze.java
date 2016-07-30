/**
 * Maze.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List; //Testing
import java.util.ArrayList; //Testing
import java.util.Random;


public class Maze {

    // Only _content is serialized, not Maze, so @SerializedName isn't needed below.
    private Content _content;
    private int _height;
    private long _id;
    private Map<Long, Line> _lines;
    private Random _random;
    private int _width;


    public Maze(long mazeId, int height, int width) {
        assert height >= 10;
        assert width >= 10;

        _height = height;
        _id = mazeId;
        _lines = new HashMap();
        _random = new Random(mazeId);
        _width = width;
    }

    public Line addHorizontal(int x, int y, int length) {
        return addLine(x, y, 0, length);
    }

    /**
     * Add a line at the specified coordinates.
     * Merge with existing lines if possible.
     */
    public Line addLine(int x, int y, int angle, int length) {
        assert angle >= 0;
        assert length >= 0;
        assert y >= 0;
        assert y >= 0;

        Line line = getLine(x, y, angle);
        if (line == null) {
            line = new Line(this, x, y, angle, length);
            putLine(line.getEndTuple(), line);
        } else {
            line.extend(length);
        }
        return line;
    }

    public Line addVertical(int x, int y, int length) {
        return addLine(x, y, 90, length);
    }

    public Content getContent() {
        if (_content == null) {
            generate();
            assert _lines != null;
            _content = new Content(_lines.values());
        }

        return _content;
    }

    public void generate() {
        generate(-_width / 2, -_height / 2, _width, _height);
    }

    public void generate(int x, int y, int w, int h) {
        assert h >= 0;
        assert w >= 0;

        _content = null;
        if (w >= 2 && h >= 2) {
            // Bisect horizontally and vertically at (px, py).
            int rx = w == 2 ? 1 : 1 + randomInt(w - 1);
            int ry = h == 2 ? 1 : 1 + randomInt(h - 1);
            assert rx > 0 && rx < w && ry > 0 && ry < h;
            int px = x + rx;
            int py = y + ry;
            Line vertical = addVertical(px, y, h);
            Line horizontal = addHorizontal(x, py, w);

            // Flip a coin as to whether to cut the horizontal twice or the vertical.
            boolean cutHorizontal = randomInt(2) == 0;
            if (cutHorizontal) {
                // Make the rightmost cut first so the pointer stays valid.
                horizontal.cut(rx + randomInt(w - rx));
                horizontal.cut(randomInt(rx));
                vertical.cut(randomInt(ry));
            } else {
                // Make the bottomost cut first so the pointer stays valid.
                vertical.cut(ry + randomInt(h - ry));
                vertical.cut(randomInt(ry));
                horizontal.cut(randomInt(rx));
            }

            // Recursively build the sub-mazes.
            generate(px, y, w - rx, ry); // Quadrant I if (0, 0) is top left.
            generate(x, y, rx, ry); // Quadrant II " ".
            generate(x, py, rx, h - ry); // Quadrant III " ".
            generate(px, y, w - rx, h - ry); // Quadrant IV " ".
        }
    }

    public long getId() {
        return _id;
    }

    public Line getLine(int x, int y, int angle) {
        assert _lines != null;
        return _lines.get(tuple(x, y, angle));
    }

    public void putLine(Long key, Line line) {
        assert _lines != null;

        _lines.put(key, line);
    }

    public int randomInt(int limit) {
        return _random.nextInt(limit);
    }

    public void removeLine(Long key) {
        assert _lines != null;

        Line line = _lines.remove(key);
        assert line != null;
    }

    private static Long tuple(int x, int y, int angle) {
        return new Long(((angle & 0xFFF) << 48) | ((y & 0xFFFFF) << 24) | (x & 0xFFFFF));
    }

    public static class Content {
        @SerializedName("Lines")
        Collection<Line> _lines;

        public Content(Collection<Line> lines) {
            assert lines != null;
            _lines = new ArrayList<Line>(lines);
            // Populate "end" point.
            for (Line i : _lines) {
                i.getEnd();
            }
        }

        public Collection<Line> getLines() {
            return _lines;
        }
    }

    public static class Line {

        private transient int _angle;
        @SerializedName("A")
        private Point _start; // Must precede "b" here so that it does in the JSON.
        @SerializedName("B")
        private Point _endCache;
        private transient int _length;
        private transient Maze _maze;


        public Line(Maze maze, int x, int y, int angle, int length) {
            _angle = angle;
            _length = length;
            _maze = maze;
            _start = new Point(x, y);
        }

        public void cut(int length) {
            assert length >= 0;
            assert _maze != null;

            int oldLength = _length;
            int diff = _length - length;
            if (diff == 1) {
                _maze.removeLine(getEndTuple());
                _length = length;
                _endCache = null;
                _maze.putLine(getEndTuple(), this);
            } else if (diff > 1) {
                _maze.removeLine(getEndTuple());
                _length = length + 1;
                _endCache = null;
                Point start = getEnd();
                _length = length;
                _endCache = null;

                _maze.putLine(getEndTuple(), this);
                _maze.addLine(start.getX(), start.getY(), _angle, oldLength - _length - 1);
            }
        }

        public void extend(int length) {
            assert length >= 0;
            assert _maze != null;

            _maze.removeLine(getEndTuple());
            _length += length;
            _endCache = null;
            _maze.putLine(getEndTuple(), this);
        }

        public Point getEnd() {
            if (_endCache == null) {
                Point start = getStart();
                int x;
                int y;
                switch (_angle) {
                    case 0:
                        x = start.getX() + _length;
                        y = start.getY();
                        break;

                    case 90:
                        x = start.getX();
                        y = start.getY() + _length;
                        break;

                    default:
                        x = y = -1;
                        assert false;
                }
                _endCache = new Point(x, y);
            }

            return _endCache;
        }

        public Long getEndTuple() {
            Point end = getEnd();
            return Maze.tuple(end.getX(), end.getY(), _angle);
        }

        public Point getStart() {
            assert _start != null;
            return _start;
        }
    }

    public static class Point {

        @SerializedName("x")
        private int _x;

        @SerializedName("y")
        private int _y;


        public Point(int x, int y) {
            _x = x;
            _y = y;
        }

        public int getX() {
            return _x;
        }

        public int getY() {
            return _y;
        }
    }
}
