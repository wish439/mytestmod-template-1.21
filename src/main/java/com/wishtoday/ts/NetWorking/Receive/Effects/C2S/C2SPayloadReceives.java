package com.wishtoday.ts.NetWorking.Receive.Effects.C2S;

import com.wishtoday.ts.Mytestmod;
import com.wishtoday.ts.NetWorking.Receive.Effects.AutoRegister;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface C2SPayloadReceives {
    void keepInventoryReceive();
    void TestPayloadReceive();
    void ToDeadPayloadReceive();
    void ModifyToBedrockReceive();
    void DamagePayloadReceive();
    void ForWardAndBackC2SReceive();
    default void register() {
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AutoRegister.class)) {
                Mytestmod.LOGGER.info("Registering " + method.getName());
                try {
                    method.invoke(this);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("Failed to auto-register method: " + method.getName(), e);
                }
            }
        }
    }
}
