import pandas as pd

THRESHOLD = 5

def load_logs(path):

    rows = []

    with open(path) as file:

        for line in file:

            line = line.strip()

            if not line:
                continue

            parts = line.split()

            rows.append({
                "date": parts[0],
                "time": parts[1],
                "ip": parts[2],
                "user": parts[3],
                "status": parts[4]
            })

    return pd.DataFrame(rows)


def detect_bruteforce(df):

    failed = df[
        df["status"] == "FAIL"
    ]

    grouped = (
        failed
        .groupby(["ip", "user"])
        .size()
        .reset_index(name="attempts")
    )

    suspicious = grouped[
        grouped["attempts"] >= THRESHOLD
    ]

    return suspicious


def statistics(df):

    return {

        "total_logs": len(df),

        "failed_attempts":
        len(df[df["status"] == "FAIL"]),

        "successful_logins":
        len(df[df["status"] == "SUCCESS"]),

        "unique_ips":
        df["ip"].nunique()
    }