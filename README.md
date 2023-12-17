# Information
This class generates a stock market variation simulation
* Default limits: 100 stocks
* Time period: 240 months (20 years)
* Each ticket starts with value: $(10 + rand(90)), i.e., $10 up to $100
* And the monthly variation is +/- (1 + rand(2))% , i.e, -3% up to +3%  
* It generates the ticket name, i.e., ABCD# where # in {3, 4, 11}
* And saves into a CSV report file, ready to be imported into Google Spreadsheet
* See: my google drive folder: personal / stocks simulation
