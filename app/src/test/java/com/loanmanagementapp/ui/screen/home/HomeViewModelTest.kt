import com.loanmanagementapp.data.repository.FakeLoanRepository
import com.loanmanagementapp.ui.screen.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testLoansAreLoadedCorrectly() = runTest {
        val viewModel = HomeViewModel(FakeLoanRepository())

        viewModel.loadLoans()
        testDispatcher.scheduler.advanceUntilIdle()

        val loans = viewModel.loans.value
        assertEquals(2, loans.size)
        assertEquals("Fake Loan", loans[0].name)
    }
}
