package art.farbfetzen.artutils.delaunay;

import org.junit.jupiter.api.Test;

import art.farbfetzen.artutils.Vector2D;

import static org.assertj.core.api.Assertions.assertThat;

class TriangleTest {

    private static final Triangle triangle = new Triangle(new Vector2D(-2, 0), new Vector2D(0, -2), new Vector2D(2, 2));

    @Test
    void shouldContainPoints() {
        assertThat(triangle.containsPoint(new Vector2D(0, 0))).isTrue();
    }

    @Test
    void shouldNotContainPoints() {
        final Vector2D outside = new Vector2D(3, 4);
        assertThat(triangle.containsPoint(outside)).isFalse();
    }

    @Test
    void shouldNotContainPointsOnCorners() {
        assertThat(triangle.containsPoint(triangle.getA())).isFalse();
        assertThat(triangle.containsPoint(triangle.getB())).isFalse();
        assertThat(triangle.containsPoint(triangle.getC())).isFalse();
    }

    @Test
    void shouldNotContainPointsOnEdges() {
        // FIXME: What's wrong here? Even (-2, -2) should not be contained!
        //  But a triangle that has no negative corners works? Maybe it's the orientation?
        //  Or I made a typo when copying the equation?

        final Vector2D onEdgeAB = new Vector2D(-1, -1);
        assertThat(triangle.containsPoint(onEdgeAB)).isFalse();
        final Vector2D onEdgeAC = new Vector2D(0, 1);
        assertThat(triangle.containsPoint(onEdgeAC)).isFalse();
        final Vector2D onEdgeBC = new Vector2D(1, 0);
        assertThat(triangle.containsPoint(onEdgeBC)).isFalse();
    }

}