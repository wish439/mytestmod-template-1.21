package com.wishtoday.ts.NetWorking.Receive;

import com.wishtoday.ts.NetWorking.Receive.Effects.C2S.Inputs.KeepInventoryReceive;
import com.wishtoday.ts.NetWorking.Receive.Effects.C2S.Inputs.ToDeadPayloadReceive;
import com.wishtoday.ts.NetWorking.Receive.Effects.C2S.ModifyToBedrock.ModifyToBedrockReceive;
import com.wishtoday.ts.NetWorking.Receive.Effects.C2S.Inputs.TestPayloadReceive;

public class RegisterReceive {
    public static void register(){
        TestPayloadReceive.receive();
        ModifyToBedrockReceive.receive();
        KeepInventoryReceive.receive();
        ToDeadPayloadReceive.receive();
    }
}
