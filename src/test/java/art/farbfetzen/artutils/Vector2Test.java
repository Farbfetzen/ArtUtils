package art.farbfetzen.artutils;

import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.assertj.core.api.Assertions.assertThat;

class Vector2Test {

    @Test
    void emptyConstructor() {
        final Vector2 vec = new Vector2();
        assertThat(vec.getX()).isZero();
        assertThat(vec.getY()).isZero();
    }

    @Test
    void copy() {
        final Vector2 vec = new Vector2(1.2, 3.4);
        assertThat(vec.copy()).isEqualTo(vec);
    }

    @Test
    void fromAndToPVector() {
        final PVector pVec = new PVector(1.2f, 3.4f);
        final Vector2 vec2 = Vector2.fromPVector(pVec);
        assertThat(vec2.toPVector()).isEqualTo(pVec);
    }

    @Test
    void updateWithVector2() {
        final Vector2 vec2 = new Vector2(1.2, 3.4);
        final Vector2 expected = new Vector2(5.6, 7.8);
        vec2.update(expected);
        assertThat(vec2).isEqualTo(expected);
    }

    @Test
    void updateWithPVector() {
        final Vector2 vec2 = new Vector2(1.2, 3.4);
        final PVector pVec = new PVector(5.6f, 7.8f);
        vec2.update(pVec);
        assertThat(vec2).isEqualTo(Vector2.fromPVector(pVec));
    }

    @Test
    void updateWithDoubles() {
        final Vector2 vec2 = new Vector2(1.2, 3.4);
        final double x = 5.6;
        final double y = 7.8;
        vec2.update(x, y);
        assertThat(vec2).isEqualTo(new Vector2(x, y));
    }

    @Test
    void add() {
        final Vector2 a = new Vector2(1.2, 3.4);
        final double s = 5.6;
        final Vector2 b = new Vector2(s, s);
        final Vector2 expected = new Vector2(a.getX() + s, a.getY() + s);
        final Vector2 c = new Vector2();
        Vector2.add(a, b, c);
        assertThat(a.copy().add(s)).isEqualTo(expected);
        assertThat(a.copy().add(b)).isEqualTo(expected);
        assertThat(Vector2.add(a, b)).isEqualTo(expected);
        assertThat(c).isEqualTo(expected);
    }

    @Test
    void sub() {
        final Vector2 a = new Vector2(1.2, 3.4);
        final double s = 0.4;
        final Vector2 b = new Vector2(s, s);
        final Vector2 expected = new Vector2(a.getX() - s, a.getY() - s);
        final Vector2 c = new Vector2();
        Vector2.sub(a, b, c);
        assertThat(a.copy().sub(s)).isEqualTo(expected);
        assertThat(a.copy().sub(b)).isEqualTo(expected);
        assertThat(Vector2.sub(a, b)).isEqualTo(expected);
        assertThat(c).isEqualTo(expected);
    }

    @Test
    void mul() {
        final Vector2 a = new Vector2(1.2, 3.4);
        final double s = 3.141;
        final Vector2 b = new Vector2(s, s);
        final Vector2 expected = new Vector2(a.getX() * s, a.getY() * s);
        final Vector2 c = new Vector2();
        Vector2.mul(a, b, c);
        assertThat(a.copy().mul(s)).isEqualTo(expected);
        assertThat(a.copy().mul(b)).isEqualTo(expected);
        assertThat(Vector2.mul(a, b)).isEqualTo(expected);
        assertThat(c).isEqualTo(expected);
    }

    @Test
    void div() {
        final Vector2 a = new Vector2(1.2, 3.4);
        final double s = 0.7;
        final Vector2 b = new Vector2(s, s);
        final Vector2 expected = new Vector2(a.getX() / s, a.getY() / s);
        final Vector2 c = new Vector2();
        Vector2.div(a, b, c);
        assertThat(a.copy().div(s)).isEqualTo(expected);
        assertThat(a.copy().div(b)).isEqualTo(expected);
        assertThat(Vector2.div(a, b)).isEqualTo(expected);
        assertThat(c).isEqualTo(expected);
    }
}