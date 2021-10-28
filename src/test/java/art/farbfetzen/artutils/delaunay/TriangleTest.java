package art.farbfetzen.artutils.delaunay;

import org.junit.jupiter.api.Test;

import art.farbfetzen.artutils.Vector2D;

import static org.assertj.core.api.Assertions.assertThat;

class TriangleTest {

    private static final Triangle triangle = new Triangle(new Vector2D(-1, 0), new Vector2D(0, -1), new Vector2D(1, 1));

    @Test
    void shouldContainPoints() {
        assertThat(triangle.containsPoint(new Vector2D(0, 0))).isTrue();
    }

    @Test
    void shouldNotContainPoints() {
        assertThat(triangle.containsPoint(new Vector2D(3, 4))).isFalse();
        assertThat(triangle.containsPoint(new Vector2D(-2, -2))).isFalse();
    }

    @Test
    void shouldNotContainPointsOnCorners() {
        assertThat(triangle.containsPoint(triangle.getA())).isFalse();
        assertThat(triangle.containsPoint(triangle.getB())).isFalse();
        assertThat(triangle.containsPoint(triangle.getC())).isFalse();
    }

    @Test
    void shouldNotContainPointsOnEdges() {
        final Vector2D onEdgeAB = Vector2D.lerp(triangle.getA(), triangle.getB(), 0.25);
        assertThat(triangle.containsPoint(onEdgeAB)).isFalse();
        final Vector2D onEdgeAC = Vector2D.lerp(triangle.getA(), triangle.getC(), 0.5);
        assertThat(triangle.containsPoint(onEdgeAC)).isFalse();
        final Vector2D onEdgeBC = Vector2D.lerp(triangle.getB(), triangle.getC(), 0.75);
        assertThat(triangle.containsPoint(onEdgeBC)).isFalse();
    }

}