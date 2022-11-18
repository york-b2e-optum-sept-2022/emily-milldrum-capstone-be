package net.yorksolutions.emilymilldrumcapstonebe.stage;
import net.yorksolutions.emilymilldrumcapstonebe.process.ProcessDTO;
import net.yorksolutions.emilymilldrumcapstonebe.stageOptions.StageOptions;

import java.util.List;

public class StageDTO extends ProcessDTO {
    public String question;
    public Integer stageOrder;
    public String type;
    public List<StageOptions> stageOptions;
}
