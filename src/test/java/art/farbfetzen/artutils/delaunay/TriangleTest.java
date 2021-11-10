package art.farbfetzen.artutils.delaunay;

import org.junit.jupiter.api.Test;

import art.farbfetzen.artutils.Vector2;

import static org.assertj.core.api.Assertions.assertThat;

class TriangleTest {

    private static final Triangle triangle = new Triangle(new Vector2(-1, 0), new Vector2(0, -1), new Vector2(1, 1));

    @Test
    void shouldContainPoints() {
        assertThat(triangle.containsPoint(new Vector2(0, 0))).isTrue();
    }

    @Test
    void shouldNotContainPoints() {
        assertThat(triangle.containsPoint(new Vector2(3, 4))).isFalse();
        assertThat(triangle.containsPoint(new Vector2(-2, -2))).isFalse();
    }

    @Test
    void shouldNotContainPointsOnCorners() {
        assertThat(triangle.containsPoint(triangle.getA())).isFalse();
        assertThat(triangle.containsPoint(triangle.getB())).isFalse();
        assertThat(triangle.containsPoint(triangle.getC())).isFalse();
    }

    @Test
    void shouldNotContainPointsOnEdges() {
        final Vector2 onEdgeAB = Vector2.lerp(triangle.getA(), triangle.getB(), 0.25);
        assertThat(triangle.containsPoint(onEdgeAB)).isFalse();
        final Vector2 onEdgeAC = Vector2.lerp(triangle.getA(), triangle.getC(), 0.5);
        assertThat(triangle.containsPoint(onEdgeAC)).isFalse();
        final Vector2 onEdgeBC = Vector2.lerp(triangle.getB(), triangle.getC(), 0.75);
        assertThat(triangle.containsPoint(onEdgeBC)).isFalse();
    }

}