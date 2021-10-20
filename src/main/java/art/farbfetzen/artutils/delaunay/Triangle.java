package art.farbfetzen.artutils.delaunay;

import lombok.Getter;

import art.farbfetzen.artutils.Vector2D;

class Triangle {

    @Getter
    private final Vector2D a;

    @Getter
    private final Vector2D b;

    @Getter
    private final Vector2D c;

    private final double areaTimesTwo;

    Triangle(final Vector2D a, final Vector2D b, final Vector2D c) {
        this.a = a;
        this.b = b;
        this.c = c;

        // Since the corners are final the area needs to be calculated only once.
        areaTimesTwo = -b.getY() * c.getX()
                + a.getY() * (-b.getX() + c.getX())
                + a.getX() * (b.getY() - c.getY())
                + b.getX() * c.getY();
    }

    /**
     * Check if a point lies inside the triangle.
     * Points on the corners or the edges are considered outside.
     * I got the algorithm from there: https://stackoverflow.com/a/14382692/16724834
     * Only works if area is positive which it only is if corners a-b-c are ordered clockwise.
     * I use screen coordinates where y increases downwards.
     */
    boolean containsPoint(final Vector2D p) {
        // TODO: Doesn't this use the cross product or something? Implement something in Vector2D and use it here.
        final double s = a.getY() * c.getX() - a.getX() * c.getY() + (c.getY() - a.getY()) * p.getX()
                + (a.getX() - c.getX()) * p.getY();
        final double t = a.getX() * b.getY() - a.getY() * b.getX() + (a.getY() - b.getY()) * p.getX()
                + (b.getX() - a.getX()) * p.getY();
        return s > 0 && s + t < areaTimesTwo;
    }

}
