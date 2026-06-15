# Instalar requirements.txt:
#pip install -r requirements.txt
#luego ejecutar:
#streamlit run app.py
import streamlit as st
import pandas as pd
import plotly.express as px

from fraud_detector import (
    load_transactions,
    detect_high_amount,
    detect_night_transactions,
    calculate_statistics
)

st.set_page_config(
    page_title="Fraud Detection Dashboard",
    layout="wide"
)

st.title("Fraud Detection Dashboard")

df = load_transactions(
    "data/transactions.csv"
)

stats = calculate_statistics(df)

st.header("General Statistics")

col1, col2, col3 = st.columns(3)

with col1:
    st.metric(
        "Transactions",
        stats["total_transactions"]
    )

with col2:
    st.metric(
        "Total Amount",
        f"${stats['total_amount']}"
    )

with col3:
    st.metric(
        "Average Amount",
        f"${stats['average_amount']}"
    )

st.divider()

st.header("Transactions Data")

st.dataframe(df)

st.divider()

st.header("High Amount Detection")

high_amount = detect_high_amount(df)

st.dataframe(high_amount)

st.write(
    f"Suspicious Transactions: {len(high_amount)}"
)

st.divider()

st.header("Night Transactions")

night = detect_night_transactions(df)

st.dataframe(night)

st.write(
    f"Night Operations: {len(night)}"
)

st.divider()

st.header("Amount Distribution")

fig = px.histogram(
    df,
    x="amount",
    nbins=10,
    title="Transaction Amount Distribution"
)

st.plotly_chart(
    fig,
    use_container_width=True
)

st.divider()

st.header("Transactions by Country")

country_chart = px.bar(
    df.groupby("country")
      .size()
      .reset_index(name="count"),
    x="country",
    y="count",
    title="Transactions by Country"
)

st.plotly_chart(
    country_chart,
    use_container_width=True
)

st.divider()

st.header("Top Suspicious Countries")

suspicious_country = (
    high_amount
    .groupby("country")
    .size()
    .reset_index(name="alerts")
)

if len(suspicious_country) > 0:

    fig2 = px.pie(
        suspicious_country,
        names="country",
        values="alerts",
        title="Fraud Alerts by Country"
    )

    st.plotly_chart(
        fig2,
        use_container_width=True
    )

else:

    st.info(
        "No suspicious countries detected."
    )