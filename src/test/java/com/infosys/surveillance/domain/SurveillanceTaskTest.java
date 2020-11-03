package com.infosys.surveillance.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.infosys.surveillance.web.rest.TestUtil;

public class SurveillanceTaskTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SurveillanceTask.class);
        SurveillanceTask surveillanceTask1 = new SurveillanceTask();
        surveillanceTask1.setId(1L);
        SurveillanceTask surveillanceTask2 = new SurveillanceTask();
        surveillanceTask2.setId(surveillanceTask1.getId());
        assertThat(surveillanceTask1).isEqualTo(surveillanceTask2);
        surveillanceTask2.setId(2L);
        assertThat(surveillanceTask1).isNotEqualTo(surveillanceTask2);
        surveillanceTask1.setId(null);
        assertThat(surveillanceTask1).isNotEqualTo(surveillanceTask2);
    }
}
