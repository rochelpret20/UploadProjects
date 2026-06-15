#instar pip install -r requirements.txt
import streamlit as st
import plotly.express as px

from log_analyzer import (
    load_logs,
    detect_bruteforce,
    statistics
)

from database import (
    create_database,
    save_incident
)

create_database()

st.set_page_config(
    page_title="Simple SIEM",
    layout="wide"
)

st.title("Simple SIEM Dashboard")

df = load_logs(
    "data/auth.log"
)

stats = statistics(df)

st.header("Security Metrics")

c1, c2, c3, c4 = st.columns(4)

c1.metric(
    "Total Logs",
    stats["total_logs"]
)

c2.metric(
    "Failed Attempts",
    stats["failed_attempts"]
)

c3.metric(
    "Successful Logins",
    stats["successful_logins"]
)

c4.metric(
    "Unique IPs",
    stats["unique_ips"]
)

st.divider()

st.header("Authentication Logs")

st.dataframe(df)

st.divider()

st.header("Brute Force Detection")

alerts = detect_bruteforce(df)

st.dataframe(alerts)

for _, row in alerts.iterrows():

    save_incident(
        row["ip"],
        row["user"],
        int(row["attempts"]),
        "Brute Force"
    )

st.divider()

st.header("Top Attack Sources")

failed = (
    df[df["status"] == "FAIL"]
    .groupby("ip")
    .size()
    .reset_index(name="count")
)

fig = px.bar(
    failed,
    x="ip",
    y="count",
    title="Failed Attempts by IP"
)

st.plotly_chart(
    fig,
    use_container_width=True
)

st.divider()

st.header("Attack Distribution")

fig2 = px.pie(
    failed,
    names="ip",
    values="count",
    title="Attack Sources"
)

st.plotly_chart(
    fig2,
    use_container_width=True
)

st.success(
    f"{len(alerts)} brute force attacks detected"
)