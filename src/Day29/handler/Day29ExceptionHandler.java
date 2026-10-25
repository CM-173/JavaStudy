package Day29.handler;

import Day28.exception.BusinessException;

public class Day29ExceptionHandler {

    public void execute(Runnable action) {

        try {
            action.run();

        } catch (BusinessException e) {
            handleBusinessException(e);

        } catch (RuntimeException e) {
            handleSystemException(e);
        }
    }

    private void handleBusinessException(BusinessException e) {
        System.out.println("业务处理失败：" + e.getMessage());
    }

    private void handleSystemException(RuntimeException e) {
        System.out.println("系统处理失败，请检查程序或数据库。");
    }
}
