/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import operations.GenericOperation;
import request.Request;
import request.RequestOperation;
import response.Response;

/**
 *
 * @author nikolab
 */
public class OperationFinder {

    private static final Map<RequestOperation, Class> SUPPORTED_OPERATIONS = new HashMap<>();

    static {
        String packageName = "operations.impl";
        String path = packageName.replaceAll("\\.", File.separator);
        String[] classPathEntries = System.getProperty("java.class.path").split(
                System.getProperty("path.separator")
        );

        String name;
        for (String classpathEntry : classPathEntries) {
            try {
                File base = new File(classpathEntry + File.separatorChar + path);
                URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://" + base.getAbsolutePath() + "/")});

                RequestOperation requestOperation;
                for (File file : Arrays.asList(base.listFiles((File dir, String n) -> n.endsWith(".class")))) {
                    name = file.getName();
                    if (name.endsWith(".class")) {
                        name = name.substring(0, name.length() - 6);
                        Class operationClass = Class.forName(packageName + "." + name);
                        requestOperation = (RequestOperation) operationClass
                                .getMethod("getSupportedOperation")
                                .invoke(operationClass.newInstance());
                        SUPPORTED_OPERATIONS.put(requestOperation, operationClass);
                    }
                }
            } catch (Exception ex) {
                // Silence is gold
            }
        }
    }
    
    public static GenericOperation findOperation(RequestOperation operation) {
        try {
            return (GenericOperation) SUPPORTED_OPERATIONS.get(operation).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
