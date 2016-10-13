When(/^I request a reservation from "([^"]*)" to "([^"]*)"$/) do |from_date, to_date|
  visit ReservationPage do |page|
    page.requestStartDate = from_date
    page.requestEndDate = to_date
    page.reserve
  end
end

Then(/^it is confirmed$/) do
  on ReservationPage do |page|
    expect(page.startDate).not_to be_nil
    expect(page.endDate).not_to be_nil
  end
end
