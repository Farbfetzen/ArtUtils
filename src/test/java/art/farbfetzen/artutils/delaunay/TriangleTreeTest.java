package art.farbfetzen.artutils.delaunay;

import org.junit.jupiter.api.Test;

import art.farbfetzen.artutils.Vector2;

import static org.assertj.core.api.Assertions.assertThat;

class TriangleTreeTest {

    @Test
    void insertAndExtract() {
        final Vector2 a = new Vector2(-1, -1);
        final Vector2 b = new Vector2(0, 8);
        final Vector2 c = new Vector2(11, 0);
        final Vector2 d = new Vector2(4, 1);
        final Vector2 e = new Vector2(1, 2.5);
        final Vector2 f = new Vector2(1.5, 1);

        final TriangleTree tree = new TriangleTree(new Triangle(a, b, c));

        assertThat(tree.insert(d)).isTrue();
        assertThat(tree.insert(e)).isTrue();
        assertThat(tree.insert(f)).isTrue();

        assertThat(tree.insert(new Vector2(3, -3))).isFalse();

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
