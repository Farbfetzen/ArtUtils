package art.farbfetzen.artutils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import processing.core.PVector;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Vector2 {

    private double x;
    private double y;

    public Vector2() {
        x = 0.0;
        y = 0.0;
    }

    public Vector2(final Vector2 other) {
        x = other.x;
        y = other.y;
    }

    public Vector2(final PVector other) {
        x = other.x;
        y = other.y;
    }

    public Vector2 copy() {
        return new Vector2(x, y);
    }

    public void update(final Vector2 other) {
        x = other.x;
        y = other.y;
    }

    public void update(final PVector other) {
        x = other.x;
        y = other.y;
    }

    public void update(final double newX, final double newY) {
        x = newX;
        y = newY;
    }

    public float getXf() {
        return (float) x;
    }

    public float getYf() {
        return (float) y;
    }

    public PVector toPVector() {
        return new PVector(getXf(), getYf());
    }

    public void add(final double scalar) {
        x += scalar;
        y += scalar;
    }

    public void add(final Vector2 other) {
        x += other.x;
        y += other.y;
    }

    public static Vector2 add(final Vector2 a, final Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public static void add(final Vector2 a, final Vector2 b, final Vector2 target) {
        target.x = a.x + b.x;
        target.y = a.y + b.y;
    }

    public void sub(final double scalar) {
        x -= scalar;
        y -= scalar;
    }

    public void sub(final Vector2 other) {
        x -= other.x;
        y -= other.y;
    }

    public static Vector2 sub(final Vector2 a, final Vector2 b) {
        return new Vector2(a.x - b.x, a.y - b.y);
    }

    public static void sub(final Vector2 a, final Vector2 b, final Vector2 target) {
        target.x = a.x - b.x;
        target.y = a.y - b.y;
    }

    public void mul(final double scalar) {
        x *= scalar;
        y *= scalar;
    }

    public void mul(final Vector2 other) {
        x *= other.x;
        y *= other.y;
    }

    public static Vector2 mul(final Vector2 a, final Vector2 b) {
        return new Vector2(a.x * b.x, a.y * b.y);
    }

    public static void mul(final Vector2 a, final Vector2 b, final Vector2 target) {
        target.x = a.x * b.x;
        target.y = a.y * b.y;
    }

    public void div(final double scalar) {
        x /= scalar;
        y /= scalar;
    }

    public void div(final Vector2 other) {
        x /= other.x;
        y /= other.y;
    }

    public static Vector2 div(final Vector2 a, final Vector2 b) {
        return new Vector2(a.x / b.x, a.y / b.y);
    }

    public static void div(final Vector2 a, final Vector2 b, final Vector2 target) {
        target.x = a.x / b.x;
        target.y = a.y / b.y;
    }

    /**
     * @param other the other vector.
     * @return The determinant of both vectors.
     */
    public double det(final Vector2 other) {
        return x * other.y - other.x * y;
    }

    public void lerp(final Vector2 other, final double t) {
        x = (1 - t) * x + t * other.x;
        y = (1 - t) * y + t * other.y;
    }

    public static Vector2 lerp(final Vector2 a, final Vector2 b, final double t) {
        return new Vector2(
                (1 - t) * a.x + t * b.x,
                (1 - t) * a.y + t * b.y
        );
    }

}

// TODO:
//  [x] elementwise addition, subtraction, multiplication, division
//  [ ] rotation by radians and degrees
//  [ ] fast 90 degree rotation: just switch x and y and negate one of them
//  [ ] normalization: set length to 1
//  [ ] set length to anything else
//  [ ] get length and length squared (which should be faster because no sqrt)
//  [ ] distance to other vector and distance squared
//  [ ] lerp
//  [x] determinant
//  [ ] dot product?
//  [ ] angle to other vector
//  [ ] angle to positive x (positive angle turns clockwise from there?), value between -pi and pi.
//  [ ] projection on other vector
//  [ ] other stuff that might be useful (maybe look at the Pygame and Processing docs)
//  [ ] Unittests for all of that
//  [ ] Sort the methods
//  [ ] documentation
