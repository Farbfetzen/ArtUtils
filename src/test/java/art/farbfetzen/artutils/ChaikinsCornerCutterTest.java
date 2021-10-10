package art.farbfetzen.artutils;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static art.farbfetzen.artutils.ChaikinsCornerCutter.cut;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ChaikinsCornerCutterTest {

    private static final List<PVector> TEST_SHAPE =
            Arrays.asList(new PVector(0, 0), new PVector(16, -16), new PVector(32, 16));

    @Test
    void invalidArgumentsShouldThrowExceptions() {
        final List<PVector> tooFewCorners = Arrays.asList(new PVector(1, 2), new PVector(3, 4));
        assertThatIllegalArgumentException().isThrownBy(() -> cut(tooFewCorners, 0.2f, 1, true))
                .withMessage("List must contain at least 3 corners.");
        assertThatIllegalArgumentException().isThrownBy(() -> cut(TEST_SHAPE, 0f, 1, true))
                .withMessage("Ratio must be > 0 and < 0.5.");
        assertThatIllegalArgumentException().isThrownBy(() -> cut(TEST_SHAPE, 0.5f, 1, true))
                .withMessage("Ratio must be > 0 and < 0.5.");
        assertThatIllegalArgumentException().isThrownBy(() -> cut(TEST_SHAPE, 0.2f, 0, true))
                .withMessage("Iterations must be > 0.");
    }

    @Test
    void cutClosed1Iteration() {
        final List<PVector> expected = Arrays.asList(
                new PVector(4, -4),
                new PVector(12, -12),
                new PVector(20, -8),
                new PVector(28, 8),
                new PVector(24, 12),
                new PVector(8, 4)
        );
        assertThat(cut(TEST_SHAPE, 0.25f, 1, true)).isEqualTo(expected);
    }

    @Test
    void cutClosed2Iterations() {
        final List<PVector> expected = Arrays.asList(
                new PVector(6, -6),
                new PVector(10, -10),
                new PVector(14, -11),
                new PVector(18, -9),
                new PVector(22, -4),
                new PVector(26, 4),
                new PVector(27, 9),
                new PVector(25, 11),
                new PVector(20, 10),
                new PVector(12, 6),
                new PVector(7, 2),
                new PVector(5, -2)
        );
        assertThat(cut(TEST_SHAPE, 0.25f, 2, true)).isEqualTo(expected);
    }

    @Test
    void cutOpen1Iteration() {
        final List<PVector> expected =
                Arrays.asList(new PVector(0, 0), new PVector(12, -12), new PVector(20, -8), new PVector(32, 16));
        assertThat(cut(TEST_SHAPE, 0.25f, 1, false)).isEqualTo(expected);
    }

    @Test
    void cutOpen2Iterations() {
        final List<PVector> expected = Arrays.asList(
                new PVector(0, 0),
                new PVector(9, -9),
                new PVector(14, -11),
                new PVector(18, -9),
                new PVector(23, -2),
                new PVector(32, 16)
        );
        assertThat(cut(TEST_SHAPE, 0.25f, 2, false)).isEqualTo(expected);
    }

}