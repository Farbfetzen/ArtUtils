package art.farbfetzen.artutils.delaunay;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import art.farbfetzen.artutils.Vector2D;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Triangle {

    private final Vector2D a;
    private final Vector2D b;
    private final Vector2D c;

    /**
     * Check if a point lies inside the triangle.
     */
    boolean containsPoint(final Vector2D p) {
        // TODO: Implement the barycentric technique which should be faster.
        final double abp = Vector2D.sub(b, a).det(Vector2D.sub(p, a));
        final double bcp = Vector2D.sub(c, b).det(Vector2D.sub(p, b));
        if (!sameSign(abp, bcp)) {
            return false;
        }
        final double acp = Vector2D.sub(a, c).det(Vector2D.sub(p, c));
        return sameSign(abp, acp);
    }

    private static boolean sameSign(final double p1, final double p2) {
        return p1 > 0 == p2 > 0;
    }

}
