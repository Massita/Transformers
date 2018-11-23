package com.massita.transformers.feature.transformers.fragments;

import com.massita.transformers.api.model.Transformer;

public interface TransformerFragmentContract {

    interface View {

        void setupNameListener();

        void setupTeamListener();

        void setupStrengthListener();

        void setupIntelligenceListener();

        void setupSpeedListener();

        void setupEnduranceListener();

        void setupRankListener();

        void setupCourageListener();

        void setupFirepowerListener();

        void setupSkillListener();

        void setName(String name);

        void setTeam(boolean isAutobots);

        void setTeamText(boolean isAutobots);

        void setStrengthText(int value);

        void setStrengthValue(int value);

        void setIntelligenceText(int value);

        void setIntelligenceValue(int value);

        void setSpeedText(int value);

        void setSpeedValue(int value);

        void setEnduranceText(int value);

        void setEnduranceValue(int value);

        void setRankText(int value);

        void setRankValue(int value);

        void setCourageText(int value);

        void setCourageValue(int value);

        void setFirepowerText(int value);

        void setFirepowerValue(int value);

        void setSkillText(int value);

        void setSkillValue(int value);

        void finishWithSuccess(Transformer transformer, int action);

        void finishCanceled();

    }

    interface Presenter {

        void start();

        void saveTransformer();

        void deleteTransformer();

        void updateName(String name);

        void updateTeam(boolean isAutobots);

        void updateStrength(int value);

        void updateIntelligence(int value);

        void updateSpeed(int value);

        void updateEndurance(int value);

        void updateRank(int value);

        void updateCourage(int value);

        void updateFirepower(int value);

        void updateSkill(int value);

    }

}
