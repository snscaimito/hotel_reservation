require 'watir-webdriver'
require 'page-object'
require 'page-object/page_factory'

World(PageObject::PageFactory)

MAIN_URL = 'http://localhost'

browser = Watir::Browser.new :chrome

Before do
  @browser = browser
end

at_exit do
  browser.close
end
