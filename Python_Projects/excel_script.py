import pandas as pd

if __name__ == "__main__":
    # path = input('Enter excel file absolute path: ')
    path = 'D:\Budget & Expenses.xlsx'
    df = pd.read_excel (path,sheet_name=['Summary','Expenses','Income'])
    # print (df)
    income_sheet = df.get('Income').to_dict()
    income_date = income_sheet.get("Unnamed: 1")
    income_category = income_sheet.get("Unnamed: 2")
    income_credit_amounts = income_sheet.get("Unnamed: 3")
    # print(income_date[len(income_date)-1])
    # print(income_credit_amounts)
    # print()

    expense_sheet = df.get('Expenses').to_dict()
    expense_date = expense_sheet.get("Unnamed: 1")
    expense_category = expense_sheet.get("Unnamed: 2")
    expense_debit_amounts = expense_sheet.get("Unnamed: 3")
    # print(expense_debit_amounts)

    summary_sheet = df.get('Summary').to_dict()
    summary_date = summary_sheet.get("Unnamed: 1")
    summary_category = summary_sheet.get("Unnamed: 2")
    summary_transactions = summary_sheet.get("Unnamed: 3")
    
    # Check New Incomes
    new_expenses = {}
    new_incomes = {}
    for i in range(1,len(income_date))[::-1]:
        temp = False
        for key,val in summary_date.items():
            if val == income_date[i]:
                if float(income_credit_amounts[i]) == float(summary_transactions[key]):
                    # print(f'{income_credit_amounts[i]} :-: {summary_transactions[key]}')
                    if income_category[i] == summary_category[key]:
                        # print(f'{income_credit_amounts[i]} :-: {summary_transactions[key]}')
                        # Data entry ho chuki h
                        # print(f'{income_category[i]} == {summary_category[key]}')
                        # print(f'{income_date[i]} :-: {income_category[i]} :-: {income_credit_amounts[i]}')
                        temp = True
                        break
                else:
                    # for given date, koi data nhi h with the same transaction amount
                    # print(f'{i} {key}-> {income_date[i]} :-: {income_category[i]} :-: {income_credit_amounts[i]}')
                    print(f'{i} {key} {summary_transactions[key]}-> {income_credit_amounts[i]}')
            # else:
                # no data for the give date in income_sheet
                # pass
        
        if not temp:
            
        
    # print(summary_transactions)
