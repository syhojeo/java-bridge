package bridge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class InputViewTest {

    private InputView inputView;

    @BeforeEach
    void beforeEach() {
        inputView = new InputView();
    }

    @ParameterizedTest
    @DisplayName("입력 받는 다리 길이 유효성 테스트")
    @CsvSource(value = {"123, fail", "30, fail", "24, fail", "10, success", "21, fail", "2, fail", "15, success",
                            "ㅁㄴ, fail", "as, fail", "@#!#$, fail", " \\t, fail",
                                ",null"})
    void checkBridgeSizeValidationTest(String bridgeSize, String expectedValidationResult) {

        if (expectedValidationResult.equals("success")) {
            assertThatCode(() -> inputView.checkValidationOfBridgeSize(bridgeSize))
                    .doesNotThrowAnyException();
        } else if (expectedValidationResult.equals("fail")){
            assertThatThrownBy(() -> inputView.checkValidationOfBridgeSize(bridgeSize))
                    .isInstanceOf(IllegalArgumentException.class);
        } else if (expectedValidationResult.equals("null")) {
            assertThatThrownBy(() -> inputView.checkValidationOfBridgeSize(bridgeSize))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @ParameterizedTest
    @DisplayName("입력 받는 이동 명령어 유효성 검사 테스트")
    @CsvSource(value = {"abc, fail", "u, fail", "d, fail", "U, success", "D, success", " , null",
                            "12345, fail", "!@#!@#$, fail", " \\t, fail"})
    void checkValidationOfMoveCommandTest(String inputMoveCommand, String expectedValidationResult) {

        if (expectedValidationResult.equals("success")) {
            assertThatCode(() -> inputView.checkValidationOfMoveCommand(inputMoveCommand))
                    .doesNotThrowAnyException();
        } else if (expectedValidationResult.equals("fail")){
            assertThatThrownBy(() -> inputView.checkValidationOfMoveCommand(inputMoveCommand))
                    .isInstanceOf(IllegalArgumentException.class);
        } else if (expectedValidationResult.equals("null")) {
            assertThatThrownBy(() -> inputView.checkValidationOfMoveCommand(inputMoveCommand))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @ParameterizedTest
    @DisplayName("입력 받는 게임 진행 명령어 유효성 검사 테스트")
    @CsvSource(value = {"abc, fail", "u, fail", "d, fail", "Q, success", "R, success", " , null",
            "12345, fail", "!@#!@#$, fail", " \\t, fail"})
    void checkValidationOfGameCommandTest(String inputGameCommand, String expectedValidationResult) {

        if (expectedValidationResult.equals("success")) {
            assertThatCode(() -> inputView.checkValidationOfGameCommand(inputGameCommand))
                    .doesNotThrowAnyException();
        } else if (expectedValidationResult.equals("fail")){
            assertThatThrownBy(() -> inputView.checkValidationOfGameCommand(inputGameCommand))
                    .isInstanceOf(IllegalArgumentException.class);
        } else if (expectedValidationResult.equals("null")) {
            assertThatThrownBy(() -> inputView.checkValidationOfGameCommand(inputGameCommand))
                    .isInstanceOf(NullPointerException.class);
        }
    }
}