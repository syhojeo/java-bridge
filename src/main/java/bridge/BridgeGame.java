package bridge;

import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final static InputView inputView = new InputView();
    private final static OutputView outputView = new OutputView();
    private final static BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

    public void run() {
        int tryNumber = 1;
        boolean isRetry = true;

        List<String> bridge = makeBridge();
        while (isRetry) {
            CurrentBridgeState currentBridgeState = new CurrentBridgeState();
            boolean isSuccess = tryCrossBridge(bridge, currentBridgeState);
            isRetry = retry(currentBridgeState, isSuccess, tryNumber);
            tryNumber++;
        }
    }

    private boolean tryCrossBridge(List<String> bridge, CurrentBridgeState currentBridgeState) {

        boolean isPossibleMove = true;
        for (int turn = 0; turn < bridge.size(); turn++) {
            isPossibleMove = move(bridge, currentBridgeState, turn);
            if (isPossibleMove == false) {
                break;
            }
        }
        return isPossibleMove;
    }

    private List<String> makeBridge() {

        int bridgeSize = inputView.readBridgeSize();
        List<String> bridge = bridgeMaker.makeBridge(bridgeSize);

        return bridge;
    }
    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(List<String> bridge, CurrentBridgeState currentBridgeState, int turn) {

        boolean isPossibleMove;

        String moveCommand = inputView.readMoving();
        isPossibleMove = isPossibleMove(bridge, moveCommand, turn);
        currentBridgeState.recordBridgeMove(moveCommand, isPossibleMove);
        outputView.printMap(currentBridgeState);

        return isPossibleMove;
    }

    public boolean isPossibleMove(List<String> bridge, String moveCommand, int turn) {

        String bridgeInformation = bridge.get(turn);

        if (bridgeInformation.equals(moveCommand)) {
            return true;
        }

        return false;
    }
    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     * @param currentBridgeState
     * @param isSuccess
     * @param tryNumber
     */
    public boolean retry(CurrentBridgeState currentBridgeState, boolean isSuccess, int tryNumber) {

        if (isSuccess == false) {
            String gameCommand = inputView.readGameCommand();
            if (gameCommand.equals("R")) {
                return true;
            }
        }
        outputView.printResult(currentBridgeState, isSuccess, tryNumber);
        return false;
    }
}
