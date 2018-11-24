package com.massita.transformers.feature.battle.fragments;

import com.massita.transformers.util.Battle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class WinnerFragmentPresenterTest {

    private WinnerFragmentPresenter mPresenter;

    @Mock
    WinnerFragmentContract.View mView;

    @Mock
    Battle.Results results;


    @Before
    public void setUp() throws Exception {
        mPresenter = new WinnerFragmentPresenter(mView, results);

        Mockito.when(results.getAutobotScore()).thenReturn(10);
        Mockito.when(results.getDecepticonScore()).thenReturn(5);
        Mockito.when(results.getNumberOfFights()).thenReturn(16);
    }

    @Test
    public void start() {
        // When
        mPresenter.start();

        InOrder inOrder = Mockito.inOrder(mView);
        //Then
        inOrder.verify(mView).setWinner("Autobots");
        inOrder.verify(mView).setNumberOfBattles(16);
        inOrder.verify(mView).setLosingTeam(any());
        inOrder.verify(mView).setWinningTeam(any());
    }
}