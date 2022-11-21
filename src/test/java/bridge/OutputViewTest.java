package bridge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OutputViewTest {

    OutputView outputView;

    @BeforeEach
    void beforeEach() {
        outputView = new OutputView();
    }

    @Test
    @DisplayName("다리를 이동한 결과값 출력 테스트")
    void printMap() {

        String expected = "[ O | O |   | X ]\n[   |   | O |   ]\n\n";
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        List<List<String>> bridgeResult = new ArrayList<>();
        List<String> upBridgeResult = List.of("O", "O", " ", "X");
        List<String> downBridgeResult = List.of(" ", " ", "O", " ");
        bridgeResult.add(upBridgeResult);
        bridgeResult.add(downBridgeResult);
        outputView.printMap(bridgeResult);

        assertThat(out.toString().contains(expected)).isTrue();
    }
}