package com.midtronics.project.brads_deal

import com.midtronics.project.feed.data.model.Deal
import com.midtronics.project.feed.data.model.SpecificDeal
import com.midtronics.project.feed.data.repository.FeedRepository
import com.midtronics.project.feed.ui.FeedViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FeedViewModelTest {

    // MOCK
    @Mock
    lateinit var feedRepository: FeedRepository

    // Init Class
    lateinit var feedViewModel: FeedViewModel


    @Before
    fun setUp() {
        feedViewModel = FeedViewModel(feedRepository)
    }


    @Test
    fun test_getDeal_verify() {
        // Given
        val deal = prepareMockData()
        runBlocking {
            Mockito.`when`(feedRepository.getDeals()).thenReturn(deal)
            // When
            feedViewModel.getDeals()
            // Then
            Mockito.verify(feedRepository).getDeals()
        }


    }


    private fun prepareMockData(): Deal {
        val specificDeal = SpecificDeal(
            listOf("Reebok")
            ,
            "1478965",
            "Reebok Women's Terry Pant $15",
            "Reebok Women's Terry...",
            "01/01/2020",
            "Reebok Women",
            0
        )

        return Deal(listOf(specificDeal))
    }

}