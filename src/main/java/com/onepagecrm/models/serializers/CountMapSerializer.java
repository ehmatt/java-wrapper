package com.onepagecrm.models.serializers;

import com.onepagecrm.models.internal.CountMap;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 13/04/2016.
 */
public class CountMapSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CountMapSerializer.class.getName());

    public static CountMap fromJsonObject(JSONObject countMapObject) {
        CountMap countMap = new CountMap();
        try {
            if (countMapObject.has(LETTER_1_TAG)) countMap.setOne(countMapObject.getInt(LETTER_1_TAG));
            if (countMapObject.has(LETTER_A_TAG)) countMap.setA(countMapObject.getInt(LETTER_A_TAG));
            if (countMapObject.has(LETTER_B_TAG)) countMap.setB(countMapObject.getInt(LETTER_B_TAG));
            if (countMapObject.has(LETTER_C_TAG)) countMap.setC(countMapObject.getInt(LETTER_C_TAG));
            if (countMapObject.has(LETTER_D_TAG)) countMap.setD(countMapObject.getInt(LETTER_D_TAG));
            if (countMapObject.has(LETTER_E_TAG)) countMap.setE(countMapObject.getInt(LETTER_E_TAG));
            if (countMapObject.has(LETTER_F_TAG)) countMap.setF(countMapObject.getInt(LETTER_F_TAG));
            if (countMapObject.has(LETTER_G_TAG)) countMap.setG(countMapObject.getInt(LETTER_G_TAG));
            if (countMapObject.has(LETTER_H_TAG)) countMap.setH(countMapObject.getInt(LETTER_H_TAG));
            if (countMapObject.has(LETTER_I_TAG)) countMap.setI(countMapObject.getInt(LETTER_I_TAG));
            if (countMapObject.has(LETTER_J_TAG)) countMap.setJ(countMapObject.getInt(LETTER_J_TAG));
            if (countMapObject.has(LETTER_K_TAG)) countMap.setK(countMapObject.getInt(LETTER_K_TAG));
            if (countMapObject.has(LETTER_L_TAG)) countMap.setL(countMapObject.getInt(LETTER_L_TAG));
            if (countMapObject.has(LETTER_M_TAG)) countMap.setM(countMapObject.getInt(LETTER_M_TAG));
            if (countMapObject.has(LETTER_N_TAG)) countMap.setN(countMapObject.getInt(LETTER_N_TAG));
            if (countMapObject.has(LETTER_O_TAG)) countMap.setO(countMapObject.getInt(LETTER_O_TAG));
            if (countMapObject.has(LETTER_P_TAG)) countMap.setP(countMapObject.getInt(LETTER_P_TAG));
            if (countMapObject.has(LETTER_Q_TAG)) countMap.setQ(countMapObject.getInt(LETTER_Q_TAG));
            if (countMapObject.has(LETTER_R_TAG)) countMap.setR(countMapObject.getInt(LETTER_R_TAG));
            if (countMapObject.has(LETTER_S_TAG)) countMap.setS(countMapObject.getInt(LETTER_S_TAG));
            if (countMapObject.has(LETTER_T_TAG)) countMap.setT(countMapObject.getInt(LETTER_T_TAG));
            if (countMapObject.has(LETTER_U_TAG)) countMap.setU(countMapObject.getInt(LETTER_U_TAG));
            if (countMapObject.has(LETTER_V_TAG)) countMap.setV(countMapObject.getInt(LETTER_V_TAG));
            if (countMapObject.has(LETTER_W_TAG)) countMap.setW(countMapObject.getInt(LETTER_W_TAG));
            if (countMapObject.has(LETTER_X_TAG)) countMap.setX(countMapObject.getInt(LETTER_X_TAG));
            if (countMapObject.has(LETTER_Y_TAG)) countMap.setY(countMapObject.getInt(LETTER_Y_TAG));
            if (countMapObject.has(LETTER_Z_TAG)) countMap.setZ(countMapObject.getInt(LETTER_Z_TAG));
            if (countMapObject.has(TOTAL_COUNT_TAG)) countMap.setTotalCount(countMapObject.getInt(TOTAL_COUNT_TAG));
            if (countMapObject.has(USER_ID_TAG)) countMap.setUserId(countMapObject.getString(USER_ID_TAG));
            return countMap;
        } catch (JSONException e) {
            LOG.severe("Error parsing CountMap object");
            LOG.severe(e.toString());
        }
        return countMap;
    }
}
