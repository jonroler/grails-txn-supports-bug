package testsupports

public class TestController {
  TestService testService

  def index() {
    testService.testMethod1()
    testService.testMethod2()
  }
}