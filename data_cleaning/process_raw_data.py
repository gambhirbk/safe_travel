# coding: utf-8

# Used Juypter notebook for the data analysis

# In[1]:


import pandas as pd
import numpy as np
# read the csv file
df = pd.read_csv("SPD_Crime_Data__2008_Present.csv",
                 parse_dates=['ReportDateTime'])
df.dropna(subset=['Longitude', 'Latitude'])  # removes Null values rows
df = df[df['Latitude'] != 0]  # removes the row with 0 value
df = df[df['Longitude'] != 0]  # removes the row with 0 value


# In[2]:

# filter data based on date
df['ReportDateTime'] = pd.to_datetime(df['ReportDateTime'])
start_date = '05-05-2019'
end_date = '10-12-2020'
mask = (df['ReportDateTime'] > start_date) & (df['ReportDateTime'] <= end_date)
df = df.loc[mask]


# In[67]:

# create a new column to provide distinct values
conditions = [
    (df['ReportDateTime'] > '2020-01-01') & (df['ReportDateTime']
                                             <= '2020-12-30'),
    (df['ReportDateTime'] > '2019-01-01') & (df['ReportDateTime']
                                             <= '2019-12-30')
]

values = ['1', '2']
df['DistinctYear'] = np.select(conditions, values)
df


# In[68]:

with pd.ExcelWriter('CrimeData.xlsx') as writer:
    df.to_excel(writer, sheet_name="CrimeData")

# filter data using df and output as excel

# In[69]:

OffenseGroup = df[['ReportNumber', 'OffenseParentGroup', 'DistinctYear']]
OffenseGroup


# In[70]:

with pd.ExcelWriter('OffenseGroup.xlsx') as writer:
    OffenseGroup.to_excel(writer, sheet_name="OffenseGroup")


# In[71]:

OffenseType = df[['ReportNumber', 'OffenseCode', 'Offense',
                  'OffenseParentGroup', 'DistinctYear']]
OffenseType


# In[72]:

with pd.ExcelWriter('OffenseType.xlsx') as writer:
    OffenseType.to_excel(writer, sheet_name="OffenseType")


# In[73]:

PoliceSector = df[['ReportNumber', 'Beat','Precinct', 'DistinctYear']]
PoliceSector


# In[74]:


with pd.ExcelWriter('PoliceSector.xlsx') as writer:
    PoliceSector.to_excel(writer, sheet_name="PoliceSector")


# In[75]:

neighborhood = df[['ReportNumber', 'MCPP', 'Beat', 'DistinctYear']]
neighborhood


# In[76]:

with pd.ExcelWriter('Neighborhood.xlsx') as writer:
    neighborhood.to_excel(writer, sheet_name="Neighborhood")


# In[77]:

location = df[['ReportNumber', 'Latitude', 'Longitude', '100BlockAddress',
               'DistinctYear']]
location


# In[78]:

with pd.ExcelWriter('location.xlsx') as writer:
    location.to_excel(writer, sheet_name="location")


# In[79]:

policeSector = df[['ReportNumber', 'Beat', 'Precinct', 'DistinctYear']]
policeSector


# In[80]:

with pd.ExcelWriter('policeSector.xlsx') as writer:
    policeSector.to_excel(writer, sheet_name="policeSector")


# In[81]:

offense = df[['ReportNumber', 'OffenseCode', 'Offense', 'OffenseParentGroup',
              'DistinctYear']]
offense


# In[82]:

with pd.ExcelWriter('OffenseCode.xlsx') as writer:
    offense.to_excel(writer, sheet_name="Offense")
