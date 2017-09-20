package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.CountMap;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * @author Cillian Myles <cillian@onepagecrm.com> on 13/04/2016.
 */
@SuppressWarnings("unused")
public class CountMapSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CountMapSerializer.class.getName());

    private final static CountMap DEFAULT = new CountMap();

    public static CountMap fromJsonObject(JSONObject countMapObject) {
        if (countMapObject == null) {
            return DEFAULT;
        }
        return new CountMap()
                .setOne(countMapObject.optInt(LETTER_1_TAG))
                .setA(countMapObject.optInt(LETTER_A_TAG))
                .setB(countMapObject.optInt(LETTER_B_TAG))
                .setC(countMapObject.optInt(LETTER_C_TAG))
                .setD(countMapObject.optInt(LETTER_D_TAG))
                .setE(countMapObject.optInt(LETTER_E_TAG))
                .setF(countMapObject.optInt(LETTER_F_TAG))
                .setG(countMapObject.optInt(LETTER_G_TAG))
                .setH(countMapObject.optInt(LETTER_H_TAG))
                .setI(countMapObject.optInt(LETTER_I_TAG))
                .setJ(countMapObject.optInt(LETTER_J_TAG))
                .setK(countMapObject.optInt(LETTER_K_TAG))
                .setL(countMapObject.optInt(LETTER_L_TAG))
                .setM(countMapObject.optInt(LETTER_M_TAG))
                .setN(countMapObject.optInt(LETTER_N_TAG))
                .setO(countMapObject.optInt(LETTER_O_TAG))
                .setP(countMapObject.optInt(LETTER_P_TAG))
                .setQ(countMapObject.optInt(LETTER_Q_TAG))
                .setR(countMapObject.optInt(LETTER_R_TAG))
                .setS(countMapObject.optInt(LETTER_S_TAG))
                .setT(countMapObject.optInt(LETTER_T_TAG))
                .setU(countMapObject.optInt(LETTER_U_TAG))
                .setV(countMapObject.optInt(LETTER_V_TAG))
                .setW(countMapObject.optInt(LETTER_W_TAG))
                .setX(countMapObject.optInt(LETTER_X_TAG))
                .setY(countMapObject.optInt(LETTER_Y_TAG))
                .setZ(countMapObject.optInt(LETTER_Z_TAG))
                .setTotalCount(countMapObject.optInt(TOTAL_COUNT_TAG))
                .setUserId(countMapObject.optString(USER_ID_TAG));
    }
}
