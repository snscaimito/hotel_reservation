class ReservationPage
  include PageObject
  
  page_url "http://localhost"
  
  text_field(:requestStartDate, :id => 'requestStartDate')
  text_field(:requestEndDate, :id => 'requestEndDate')
  button(:reserve, :id => 'reserve' )
  
  span(:startDate, :id => 'startDate')
  span(:endDate, :id => 'endDate')
end
