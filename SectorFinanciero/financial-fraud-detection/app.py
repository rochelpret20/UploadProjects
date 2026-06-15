import streamlit as st
import pandas as pd
import plotly.express as px

from fraud_model import detect_fraud

st.set_page_config(
    page_title="Financial Fraud Detection",
    layout="wide"
)

st.title(
    "Financial Fraud Detection System"
)

st.sidebar.header(
    "Transaction Data"
)

amount = st.sidebar.number_input(
    "Amount",
    value=100
)

hour = st.sidebar.number_input(
    "Hour",
    min_value=0,
    max_value=23,
    value=12
)

customer_age = st.sidebar.number_input(
    "Customer Age",
    value=30
)

transactions_today = st.sidebar.number_input(
    "Transactions Today",
    value=2
)

if st.sidebar.button(
    "Analyze Transaction"
):

    result = detect_fraud(
        amount,
        hour,
        customer_age,
        transactions_today
    )

    if result == -1:

        st.error(
            "Potential Fraud Detected"
        )

    else:

        st.success(
            "Transaction Appears Legitimate"
        )

st.divider()

df = pd.read_csv(
    "data/transactions.csv"
)

st.header(
    "Historical Transactions"
)

st.dataframe(df)

st.divider()

st.header(
    "Transaction Amount Distribution"
)

fig = px.histogram(
    df,
    x="amount",
    nbins=20
)

st.plotly_chart(
    fig,
    use_container_width=True
)

st.divider()

st.header(
    "Fraud vs Legitimate"
)

fraud_counts = (
    df["fraud"]
    .value_counts()
    .reset_index()
)

fraud_counts.columns = [
    "Type",
    "Count"
]

fraud_counts["Type"] = (
    fraud_counts["Type"]
    .map({
        0:"Legitimate",
        1:"Fraud"
    })
)

fig2 = px.pie(
    fraud_counts,
    names="Type",
    values="Count"
)

st.plotly_chart(
    fig2,
    use_container_width=True
)

st.divider()

st.header(
    "Fraud by Hour"
)

fraud_hours = (
    df[df["fraud"] == 1]
    .groupby("hour")
    .size()
    .reset_index(name="count")
)

fig3 = px.bar(
    fraud_hours,
    x="hour",
    y="count"
)

st.plotly_chart(
    fig3,
    use_container_width=True
)