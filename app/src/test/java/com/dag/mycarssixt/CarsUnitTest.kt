package com.dag.mycarssixt

import com.dag.mycarssixt.feature.cars.data.Car
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

val TEST_CAR_FOR_FUEL_TYPE = Car(
    fuelType = "test"
)

val TEST_CAR_FOR_TRANSMISSION_TEXT = Car(
    transmission = "test"
)

val TEST_CAR_FOR_TRANSMISSION_MANUEL_TEXT = Car(
    transmission = "M"
)


@ExperimentalCoroutinesApi
class CarsUnitTest {

    @Before
    fun setup(){}

    @Test
    fun `when test car has a fuelType param with value test,fuelTypeText returns unknown`(){
        assert(TEST_CAR_FOR_FUEL_TYPE.fuelTypeText.lowercase() == "unknown")
    }

    @Test
    fun `when test car has a transmission param with value test,transmissionText returns unknown`(){
        assert(TEST_CAR_FOR_TRANSMISSION_TEXT.transmissionText.lowercase() == "unknown")
    }

    @Test
    fun `when test car has a transmission param with value M,transmissionText returns Manuel`(){
        assert(TEST_CAR_FOR_TRANSMISSION_MANUEL_TEXT.transmissionText.lowercase() == "manuel")
    }
}