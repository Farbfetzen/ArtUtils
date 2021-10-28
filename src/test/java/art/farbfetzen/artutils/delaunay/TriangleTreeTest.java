package art.farbfetzen.artutils.delaunay;

import org.junit.jupiter.api.Test;

import art.farbfetzen.artutils.Vector2D;

import static org.assertj.core.api.Assertions.assertThat;

class TriangleTreeTest {

    @Test
    void insertAndExtract() {
        final Vector2D a = new Vector2D(-1, -1);
        final Vector2D b = new Vector2D(0, 8);
        final Vector2D c = new Vector2D(11, 0);
        final Vector2D d = new Vector2D(4, 1);
        final Vector2D e = new Vector2D(1, 2.5);
        final Vector2D f = new Vector2D(1.5, 1);

        final TriangleTree tree = new TriangleTree(new Triangle(a, b, c));

        assertThat(tree.insert(d)).isTrue();
        assertThat(tree.insert(e)).isTrue();
        assertThat(tree.insert(f)).isTrue();

        assertThat(tree.insert(new Vector2D(3, -3))).isFalse();

        assertThat(tree.getLeafNodes()).hasSize(7).containsExactlyInAnyOrder(
                new Triangle(b, c, d),
                new Triangle(c, a, d),
                new Triangle(a, b, e),
                new Triangle(b, d, e),
                new Triangle(d, a, f),
                new Triangle(a, e, f),
                new Triangle(e, d, f)
        );
    }

    // TODO: Test what happens if two points coincide or one point is on the edge of an already existing triangle.

}
