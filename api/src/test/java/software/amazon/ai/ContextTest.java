/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package software.amazon.ai;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContextTest {

    @Test
    public void testContext() {
        Assert.assertEquals(Context.cpu(), new Context("cpu", 0));
        Assert.assertEquals(Context.cpu(1), new Context("cpu", 1));
        Assert.assertEquals(Context.gpu(), new Context("gpu", 0));
        Assert.assertEquals(Context.gpu(3), new Context("gpu", 3));
        // Assert.assertTrue(Context.cpu().equals(new Context("cpu", 0)));
        Assert.assertFalse(Context.gpu().equals(Context.cpu()));
        Assert.assertFalse(Context.cpu().equals(new Context("cpu", 1)));
    }
}
