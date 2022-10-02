package de.farbfetzen.artutils;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import processing.core.PVector;

@UtilityClass
public class ChaikinsCornerCutter {

    /**
     * Cuts the corners of a shape using Chaikin's corner-cutting algorithm.
     * <p>
     * Be careful when you have lots of corners and many iterations as each iteration doubles the number of corners
     * (minus two if not closed). Usually 3 or 4 iterations are enough to produce rounded corners.
     *
     * @param corners    The list of corners to be rounded. Must contain at least 3 PVectors.
     * @param ratio      The amount of linear interpolation between two points. Must be &gt; 0 and &lt; 0.5.
     * @param iterations Number of times the algorithm is repeated.
     * @param closed     If true, then the shape is assumed to be closed and new points will be generated between
     *                   the last and the first corner. Otherwise, The original first and last corners will remain in
     *                   place.
     * @return The interpolated list of points.
     */
    public static List<PVector> cut(final List<PVector> corners,
            final float ratio,
            final int iterations,
            final boolean closed) {
        if (corners.size() < 3) {
            throw new IllegalArgumentException("List must contain at least 3 corners.");
        }
        if (ratio <= 0 || ratio >= 0.5) {
            throw new IllegalArgumentException("Ratio must be > 0 and < 0.5.");
        }
        if (iterations < 1) {
            throw new IllegalArgumentException("Iterations must be > 0.");
        }
        return closed ? cutClosed(corners, ratio, iterations) : cutOpen(corners, ratio, iterations);
    }

    private static List<PVector> cutClosed(List<PVector> corners, final float ratio, final int iterations) {
        for (int i = 0; i < iterations; i++) {
            final List<PVector> newCorners = new ArrayList<>(corners.size() * 2);
            PVector a;
            PVector b;
            for (int k = 0; k < corners.size() - 1; k++) {
                a = corners.get(k);
                b = corners.get(k + 1);
                newCorners.add(PVector.lerp(a, b, ratio));
                newCorners.add(PVector.lerp(b, a, ratio));
            }
            a = corners.get(corners.size() - 1);
            b = corners.get(0);
            newCorners.add(PVector.lerp(a, b, ratio));
            newCorners.add(PVector.lerp(b, a, ratio));
            corners = newCorners;
        }
        return corners;
    }

    private static List<PVector> cutOpen(List<PVector> corners, final float ratio, final int iterations) {
        for (int i = 0; i < iterations; i++) {
            final List<PVector> newCorners = new ArrayList<>(corners.size() * 2 - 2);
            newCorners.add(corners.get(0));
            PVector a = corners.get(0);
            PVector b = corners.get(1);
            newCorners.add(PVector.lerp(b, a, ratio));
            for (int k = 1; k < corners.size() - 2; k++) {
                a = corners.get(k);
                b = corners.get(k + 1);
                newCorners.add(PVector.lerp(a, b, ratio));
                newCorners.add(PVector.lerp(b, a, ratio));
            }
            a = corners.get(corners.size() - 2);
            b = corners.get(corners.size() - 1);
            newCorners.add(PVector.lerp(a, b, ratio));
            newCorners.add(corners.get(corners.size() - 1));
            corners = newCorners;
        }
        return corners;
    }

}
