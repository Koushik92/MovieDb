# MovieDb

**[Programming Language]** - Kotlin

**[Architecture Used]** - Model - View - ViewModel (MVVM)

**[Architecture Components Used**] - Mutable Live Data , Data Binding Library and Kotlin KTX

**[Network Layer Used]** - Retrofit and RXJAVA2

**[Image Download Library]** - Glide

**[Dependency Injection Framework]** -Dependency Injection Framework

**[Unit Test]** - JUNIT5

**[Architecture Implementation Details]** - 
- Activity (User Interaction layer) made completely free from doing business logic .
- View Model takes care of processing all business logic . It gets the data from Repository 
- Repository takes care of getting data from API . Once it gets data it passes to view model 
- Once View Model receives data ,it process the data and return back to activity through Mutable Live data .
- As soon as new data available , activity observers are invoked and displays the data to user 


**[Functional Implementation Details]** - 
- List All Movies .
- Display Movies by movie name 
- Display Movies by title date 
- Display Movie Details such as Overview ,Title  Rating and book ticket option on click f movie 
- Book Movie option redirects user to Movie booking online website 
