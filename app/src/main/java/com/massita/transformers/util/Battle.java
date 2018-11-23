package com.massita.transformers.util;

import com.massita.transformers.api.model.Transformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

public class Battle {
    private List<Transformer> autobots = new ArrayList<>();
    private List<Transformer> decepticons = new ArrayList<>();

    public Single<Battle> prepare(List<Transformer> transformers) {
        Collections.sort(transformers, (o1, o2) -> o2.getRank() - o1.getRank());

        for (Transformer t: transformers) {
            if(t.isAutobots()) {
                autobots.add(t);
            } else {
                decepticons.add(t);
            }
        }

        return Single.just(this);
    }

    public Single<Results> fight() {
        Results results = new Results();

        results.numberOfFights = getNumberOfFights();

        for(int i = 0; i < results.numberOfFights; i++) {
            Transformer autobot = autobots.get(i);
            Transformer decepticon = decepticons.get(i);

            if(autobot.isOverpower() && decepticon.isOverpower()) {
                results.setDraw(true);
                results.setAllDestroyed(true);

                return Single.just(results);
            }

            results = duel(results, autobot, decepticon);
        }

        return Single.just(results);
    }

    private Results duel(Results results, Transformer autobot, Transformer decepticon) {

        Transformer winner = getWinner(autobot, decepticon);
        if(winner == null){
            return results;
        }

        if(Transformer.TEAM_AUTOBOTS.equals(winner.getTeam())) {
            results.increaseAutobotScore();
        } else {
            results.increaseDecepticonScore();
        }

        return results;
    }
    
    private Transformer getWinner(Transformer transformer1, Transformer transformer2) {
        if(transformer1.isOverpower()) {
            return transformer1;
        }

        if(transformer2.isOverpower()) {
            return transformer2;
        }

        if(transformer1.getCourage() - transformer2.getCourage() >= 4 && transformer1.getStrength() - transformer2.getStrength() >= 3) {
            return transformer1;
        }

        if(transformer2.getCourage() - transformer1.getCourage() >= 4 && transformer2.getStrength() - transformer1.getStrength() >= 3) {
            return transformer2;
        }

        if(transformer1.getSkill() - transformer2.getSkill() >= 3) {
            return transformer1;
        }

        if(transformer2.getSkill() - transformer1.getSkill() >= 3) {
            return transformer2;
        }

        if(transformer1.getOverall() > transformer2.getOverall()) {
            return transformer1;
        }

        if(transformer2.getOverall() > transformer1.getOverall()) {
            return transformer2;
        }

        return null;
    }

    private int getNumberOfFights() {
        return Math.min(autobots.size(), decepticons.size());
    }

    public class Results {
        private int numberOfFights;
        private boolean isDraw;
        private boolean allDestroyed;
        private int autobotScore;
        private int decepticonScore;
        private List<Transformer> winningTeam;
        private List<Transformer> survivors;

        public boolean isDraw() {
            return isDraw;
        }

        public void setDraw(boolean draw) {
            isDraw = draw;
        }

        public boolean isAllDestroyed() {
            return allDestroyed;
        }

        public void setAllDestroyed(boolean allDestroyed) {
            this.allDestroyed = allDestroyed;
        }

        public void setAutobotScore(int autobotScore) {
            this.autobotScore = autobotScore;
        }

        public void setDecepticonScore(int decepticonScore) {
            this.decepticonScore = decepticonScore;
        }

        public int getAutobotScore() {
            return autobotScore;
        }

        public void increaseAutobotScore() {
            this.autobotScore++;
        }

        public int getDecepticonScore() {
            return decepticonScore;
        }

        public void increaseDecepticonScore() {
            this.decepticonScore++;
        }

        public int getNumberOfFights() {
            return numberOfFights;
        }

        public void setNumberOfFights(int numberOfFights) {
            this.numberOfFights = numberOfFights;
        }

        public List<Transformer> getWinningTeam() {
            return winningTeam;
        }

        public void setWinningTeam(List<Transformer> winningTeam) {
            this.winningTeam = winningTeam;
        }

        public List<Transformer> getSurvivors() {
            return survivors;
        }

        public void setSurvivors(List<Transformer> survivors) {
            this.survivors = survivors;
        }
    }
}
