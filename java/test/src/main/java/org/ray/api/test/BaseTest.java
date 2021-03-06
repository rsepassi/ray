package org.ray.api.test;

import java.io.File;
import org.ray.api.Ray;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

  @BeforeMethod
  public void setUp() {
    System.setProperty("ray.home", "../..");
    System.setProperty("ray.resources", "CPU:4,RES-A:4");
    System.setProperty("ray.raylet.config.inline_object_max_size_bytes", "0");
    Ray.init();
  }

  @AfterMethod
  public void tearDown() {
    // TODO(qwang): This is double check to check that the socket file is removed actually.
    // We could not enable this until `systemInfo` enabled.
    //File rayletSocketFIle = new File(Ray.systemInfo().rayletSocketName());
    Ray.shutdown();

    //remove raylet socket file
    //rayletSocketFIle.delete();

    // unset system properties
    System.clearProperty("ray.home");
    System.clearProperty("ray.resources");
    System.clearProperty("ray.raylet.config.inline_object_max_size_bytes");
  }

}
