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
package software.amazon.ai.metric;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MetricsTest {

    @Test
    public void testMetrics() {
        Metrics metrics = new Metrics();
        metrics.addMetric(new Metric("m1", 1L));
        metrics.addMetric("m1", 3L, "count");
        metrics.addMetric("m1", 2L);
        Metric p50 = metrics.percentile("m1", 50);
        Assert.assertEquals(p50.getValue().longValue(), 2L);

        metrics.addMetric(new Metric("m2", 1f));
        metrics.addMetric("m2", 3f, "count");
        metrics.addMetric("m2", 2f);
        p50 = metrics.percentile("m2", 50);
        Assert.assertEquals(p50.getValue().floatValue(), 2f);

        metrics.addMetric(new Metric("m3", 1d));
        metrics.addMetric("m3", 3d, "count");
        metrics.addMetric("m3", 2d);
        p50 = metrics.percentile("m3", 50);
        Assert.assertEquals(p50.getValue().doubleValue(), 2d);

        List<Metric> list = metrics.getMetric("m1");
        Assert.assertEquals(list.size(), 3);

        list = metrics.getMetric("m4");
        Assert.assertEquals(list.size(), 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMeanException() {
        Metrics metrics = new Metrics();
        metrics.mean("not_found");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testPercentileException() {
        Metrics metrics = new Metrics();
        metrics.percentile("not_found", 1);
    }
}
