package art.farbfetzen.artutils.delaunay;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import art.farbfetzen.artutils.Vector2;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Triangle {

    private final Vector2 a;
    private final Vector2 b;
    private final Vector2 c;

    /**
     * Check if a point lies inside the triangle.
     */
    boolean containsPoint(final Vector2 p) {
        // TODO: Implement the barycentric technique which should be faster.
        final double abp = Vector2.sub(b, a).det(Vector2.sub(p, a));
        final double bcp = Vector2.sub(c, b).det(Vector2.sub(p, b));
        if (!sameSign(abp, bcp)) {
            return false;
        }
        final double acp = Vector2.sub(a, c).det(Vector2.sub(p, c));
        return sameSign(abp, acp);
    }

    private static boolean sameSign(final double p1, final double p2) {
        return p1 > 0 == p2 > 0;
    }

}
